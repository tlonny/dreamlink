package dreamlink.utility;

import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class StringFns {

    public static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    public static String urlDecode(String encodedStr) {
        return URLDecoder.decode(encodedStr, StandardCharsets.UTF_8);
    }
    
}
