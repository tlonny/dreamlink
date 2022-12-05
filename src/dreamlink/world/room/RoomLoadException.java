package dreamlink.world.room;

public class RoomLoadException extends Exception {

    public RoomLoadException(String message) {
        super(message);
    }

    public static RoomLoadException downloadFailed(String URI) {
        var msg = String.format("Remote not found: %s", URI);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidTextureSample(String path) {
        var msg = String.format("Invalid texture sample: %s", path);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidEnvironment() {
        var msg = "Invalid environment";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidSettings() {
        var msg = "Invalid settings";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidSound(String path) {
        var msg = String.format("Invalid sound: %s", path);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException missingTextureAtlas() {
        var msg = "Missing texture atlas";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidTextureAtlas() {
        var msg = "Invalid texture atlas";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidUserBlockReservationData() {
        var msg = "Invalid user block reservation data";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException unableToAcquireUserBlockID() {
        var msg = "Unable to acquire user block ID";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidUserBlock(String path) {
        var msg = String.format("Invalid user block: %s", path);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidDoorTemplate(String path) {
        var msg = String.format("Invalid door template: %s", path);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidDoorData() {
        var msg = "Invalid door data";
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidTerrainData() {
        return new RoomLoadException("Invalid terrain data");
    }

    public static RoomLoadException invalidSkyBoxConfig() {
        return new RoomLoadException("Invalid skybox config");
    }

    public static RoomLoadException invalidAmbienceConfig() {
        return new RoomLoadException("Invalid ambience config");
    }

    public static RoomLoadException missingSound(String sound) {
        var msg = String.format("Missing sound: %s", sound);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException missingTexture(String texture) {
        var msg = String.format("Missing texture: %s", texture);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException invalidBlockID(int blockID) {
        var msg = String.format("Invalid block ID: %d", blockID);
        return new RoomLoadException(msg);
    }

    public static RoomLoadException missingTerrainFile() {
        return new RoomLoadException("Missing terrain file");
    }

    public static RoomLoadException invalidContentType(String contentType) {
        var msg = String.format("Invalid content type: %s", contentType);
        return new RoomLoadException(msg);
    }
}
