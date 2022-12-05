package dreamlink.utility.png;

public class PNGDecodeException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to decode [PNG] data";
    }
    
}
