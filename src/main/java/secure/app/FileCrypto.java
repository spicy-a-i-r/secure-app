package secure.app;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.nio.file.Path;
import java.nio.file.Files;
import java.security.SecureRandom;




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


    public static void encrypt(Path input, Path output, char[] pass) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LEN]; // CREATING A SALT USING THE SALT LENGTH
        byte[] iv = new byte[IV_LEN]; // NEW INITALIAZATION VECTOR
        random.nextBytes(salt);
        random.nextBytes(iv);

        SecretKey key = deriveKey(pass, salt);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
        byte[] ciphertext = cipher.doFinal(Files.readAllBytes(input)); // an error from here
        
        ByteBuffer out = ByteBuffer.allocate(SALT_LEN + IV_LEN + ciphertext.length);
        out.put(salt).put(iv).put(ciphertext);

        Files.write(output, out.array());
    }  

    public static void decrypt(Path input, Path output, char[] pass) throws Exception{
        ByteBuffer in = ByteBuffer.wrap(Files.readAllBytes(input)); // grabs the data from the file

        byte[] salt = new byte[SALT_LEN]; // salt length and iv should still be the same
        byte[] iv = new byte[IV_LEN];
        in.get(salt); 
        in.get(iv); 
        byte[] ciphertext = new byte[in.remaining()]; 
        in.get(ciphertext); // takes the cipher text

        SecretKey key = deriveKey(pass, salt); // gets the key

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding"); // gets the correct cipher standard?
        cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv)); // initalizes the cipher
        byte[] normalText = cipher.doFinal(ciphertext); 

        ByteBuffer out = ByteBuffer.allocate(normalText.length);
        out.put(normalText);
        Files.write(output, out.array());

    }
    

}
