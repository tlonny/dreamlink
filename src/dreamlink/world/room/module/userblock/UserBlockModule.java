package dreamlink.world.room.module.userblock;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;
import dreamlink.world.room.module.userblock.loader.BillboardBlockLoader;
import dreamlink.world.room.module.userblock.loader.CrossBlockLoader;
import dreamlink.world.room.module.userblock.loader.CubeBlockLoader;
import dreamlink.world.room.module.userblock.loader.HalfSlabBlockLoader;
import dreamlink.world.room.module.userblock.loader.InvertedHalfSlabBlockLoader;
import dreamlink.world.room.module.userblock.loader.InvertedSlabBlockLoader;
import dreamlink.world.room.module.userblock.loader.PanelBlockLoader;
import dreamlink.world.room.module.userblock.loader.SlabBlockLoader;
import dreamlink.world.room.module.userblock.loader.TileBlockLoader;

public class UserBlockModule {
    
    private static final Path blocksDirectory = Paths.get("pack/block");
    private static final Path blockReservationData = Paths.get("blockreservation.dat");

    private static final String cubeBlockConfigSuffix = ".cube.json";
    private static final String slabBlockConfigSuffix = ".slab.json";
    private static final String invertedSlabBlockConfigSuffix = ".islab.json";
    private static final String halfSlabBlockConfigSuffix = ".halfslab.json";
    private static final String invertedHalfSlabBlockConfig = ".ihalfslab.json";
    private static final String crossBlockConfigSuffix = ".cross.json";
    private static final String billboardBlockConfigSuffix = ".billboard.json";
    private static final String panelBlockConfigSuffix = ".panel.json";
    private static final String tileBlockConfigSuffix = ".tile.json";
    private static final int blockIDStart = 0x100;
    private static final int blockIDEnd = 0x3FF;

    private final IRoomModuleProvider provider;

    private final Queue<Integer> blockIDQueue = new ArrayDeque<>();
    private final Map<String, Integer> blockIDReservations = new HashMap<>();
    private final ArrayList<IUserBlock> userBlocks = new ArrayList<>();

    private final CubeBlockLoader cubeBlockLoader;
    private final BillboardBlockLoader billboardBlockLoader;
    private final SlabBlockLoader slabBlockLoader;
    private final InvertedSlabBlockLoader invertedSlabBlockLoader;
    private final InvertedHalfSlabBlockLoader invertedHalfSlabBlockLoader;
    private final HalfSlabBlockLoader halfSlabBlockLoader;
    private final CrossBlockLoader crossBlockLoader;
    private final PanelBlockLoader panelBlockLoader;
    private final TileBlockLoader tileBlockLoader;

    public UserBlockModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.cubeBlockLoader = new CubeBlockLoader(provider);
        this.billboardBlockLoader = new BillboardBlockLoader(provider);
        this.slabBlockLoader = new SlabBlockLoader(provider);
        this.invertedSlabBlockLoader = new InvertedSlabBlockLoader(provider);
        this.halfSlabBlockLoader = new HalfSlabBlockLoader(provider);
        this.invertedHalfSlabBlockLoader = new InvertedHalfSlabBlockLoader(provider);
        this.crossBlockLoader = new CrossBlockLoader(provider);
        this.panelBlockLoader = new PanelBlockLoader(provider);
        this.tileBlockLoader = new TileBlockLoader(provider);

        for(var ix = UserBlockModule.blockIDStart; ix < UserBlockModule.blockIDEnd; ix += 1) {
            this.blockIDQueue.add(ix);
        }
    }

    public int acquireBlockID(String fileName) {
        if(this.blockIDReservations.containsKey(fileName)) {
            return this.blockIDReservations.get(fileName);
        } else if(this.blockIDQueue.isEmpty()) {
            return -1;
        } else {
            var blockID = this.blockIDQueue.remove();
            return blockID;
        }
    }

    private void loadReservationData() throws RoomLoadException{
        var roomPath = this.provider.getIOModule().getPath();

        var dataFile = roomPath
            .resolve(UserBlockModule.blockReservationData)
            .toFile();

        if(!dataFile.exists()) {
            return;
        }

        try(
            var fileInputStream = new FileInputStream(dataFile);
            var bufferedInputStream = new BufferedInputStream(fileInputStream);
            var dataInputStream = new DataInputStream(bufferedInputStream);
        ) {
            var numEntries = dataInputStream.readInt();
            for(var ix = 0; ix < numEntries; ix += 1) {
                var blockID = dataInputStream.readInt();
                var fileName = dataInputStream.readUTF();
                if(!this.blockIDQueue.remove(blockID)) {
                    throw RoomLoadException.invalidUserBlockReservationData();
                }
                this.blockIDReservations.put(fileName, blockID);
            }
        } catch (IOException e) {
            throw RoomLoadException.invalidUserBlockReservationData();
        }
    }

    private void loadBlockData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var blockModule = this.provider.getBlockModule();
        var stampModule = this.provider.getStampModule();

        var blocksDirectory = roomPath
            .resolve(UserBlockModule.blocksDirectory)
            .toFile();

        if(!blocksDirectory.exists()) {
            return;
        }

        for(var file : blocksDirectory.listFiles(File::isFile)) {
            var fileName = file.getName();
            IUserBlock block;

            if(fileName.endsWith(UserBlockModule.cubeBlockConfigSuffix)) {
                block = this.cubeBlockLoader.loadCubeBlock(file);
            } else if(fileName.endsWith(UserBlockModule.billboardBlockConfigSuffix)) {
                block = this.billboardBlockLoader.loadBillboardBlock(file);
            } else if(fileName.endsWith(UserBlockModule.panelBlockConfigSuffix)) {
                block = this.panelBlockLoader.loadPanelBlock(file);
            } else if(fileName.endsWith(UserBlockModule.slabBlockConfigSuffix)) {
                block = this.slabBlockLoader.loadSlabBlock(file);
            } else if(fileName.endsWith(UserBlockModule.halfSlabBlockConfigSuffix)) {
                block = this.halfSlabBlockLoader.loadHalfSlabBlock(file);
            } else if(fileName.endsWith(UserBlockModule.crossBlockConfigSuffix)) {
                block = this.crossBlockLoader.loadCrossBlock(file);
            } else if(fileName.endsWith(UserBlockModule.invertedSlabBlockConfigSuffix)) {
                block = this.invertedSlabBlockLoader.loadInvertedSlabBlock(file);
            } else if(fileName.endsWith(UserBlockModule.invertedHalfSlabBlockConfig)) {
                block = this.invertedHalfSlabBlockLoader.loadInvertedHalfSlabBlock(file);
            } else if(fileName.endsWith(UserBlockModule.tileBlockConfigSuffix)) {
                block = this.tileBlockLoader.loadTileBlock(file);
            } else {
                continue;
            }

            this.userBlocks.add(block);
            stampModule.addStamp(block);
            blockModule.addBlock(block);
        }
    }

    public void loadData() throws RoomLoadException {
        this.loadReservationData();
        this.loadBlockData();
    }

    public void saveData() {
        var roomPath = this.provider.getIOModule().getPath();

        var dataFile = roomPath
            .resolve(UserBlockModule.blockReservationData)
            .toFile();

        try(
            var fileOutputStream = new FileOutputStream(dataFile);
            var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            var dataOutputStream = new DataOutputStream(bufferedOutputStream);
        ) {
            dataOutputStream.writeInt(this.userBlocks.size());
            for(var ix = 0; ix < this.userBlocks.size(); ix += 1) {
                var block = this.userBlocks.get(ix);
                dataOutputStream.writeInt(block.getBlockID());
                dataOutputStream.writeUTF(block.getFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
