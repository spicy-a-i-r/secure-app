package secure.app;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;


public class FileCrypto {

    private static final int SALT_LEN = 16; // private so cant be modified
    private static final int IV_LEN = 12;
    private static final int ITERATIONS = 210_000;
    private static final int KEY_BITS = 256;

    private static SecretKey deriveKey(char[] pass, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pass, salt, ITERATIONS, KEY_BITS);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES"); 
    }

    
    

}
