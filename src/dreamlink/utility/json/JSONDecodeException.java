package dreamlink.utility.json;

public class JSONDecodeException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to decode [JSON] data";
    }
    
}
