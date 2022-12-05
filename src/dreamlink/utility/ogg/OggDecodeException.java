package dreamlink.utility.ogg;

public class OggDecodeException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to decode [OGG] data";
    }
    
}
