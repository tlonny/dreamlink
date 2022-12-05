package dreamlink.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFns {

    public static BigInteger generateHash(byte[] bytes) {
        try {
            var messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            var bytesDigest = messageDigest.digest();
            return new BigInteger(1, bytesDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
