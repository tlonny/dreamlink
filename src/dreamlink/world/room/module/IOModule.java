package dreamlink.world.room.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Path;

import dreamlink.config.RepositoryConfig;
import dreamlink.disk.LocalRoomState;
import dreamlink.utility.CommonPaths;
import dreamlink.utility.StringFns;
import dreamlink.utility.file.FileFns;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class IOModule {

    private static final String downloadPathSegment = "/v1/public/room/";
    private static final String expectedContentType = "application/zip";

    private final IRoomModuleProvider provider;
    private Path path;

    public IOModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public Path getPath() {
        return this.path;
    }

    public void loadData() throws RoomLoadException {
        var roomName = this.provider.getName();
        if(LocalRoomState.instance.hasRoom(roomName)) {
            var localRoom = LocalRoomState.instance.getRoom(roomName);
            this.path = localRoom.file.toPath();
            return;
        }
        
        var hexEncodedName = StringFns.urlEncode(roomName);
        var cachePath = CommonPaths.instance.cachedRemoteRoomsPath.resolve(hexEncodedName);
        var cacheFile = cachePath.toFile();

        if(!cacheFile.exists()) {
            var remoteURI = RepositoryConfig.instance.getOrigin()
                .resolve(IOModule.downloadPathSegment)
                .resolve(String.format("%s/data", hexEncodedName));

            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) remoteURI.toURL().openConnection();
                var contentType = connection.getContentType();
                var tempFile = File.createTempFile("room", ".zip");

                if(!IOModule.expectedContentType.equals(contentType)) {
                    throw RoomLoadException.invalidContentType(contentType);
                }

                try(
                    var inputStream = connection.getInputStream();
                    var outputStream = new FileOutputStream(tempFile);
                ) {
                    inputStream.transferTo(outputStream);
                    FileFns.extractZip(tempFile, cacheFile);
                } finally {
                    tempFile.delete();
                }
            } catch(IOException e) {
                throw RoomLoadException.downloadFailed(downloadPathSegment);
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
            }
        }

        this.path = cachePath;
    }
    
}
