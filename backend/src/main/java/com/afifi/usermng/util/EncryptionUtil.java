package com.afifi.usermng.util;

import com.afifi.usermng.exception.ServiceException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;

@Component
public class EncryptionUtil {

    public String encrypt(String pass) throws ServiceException {
        try {
            byte[] ivBytes;
            String key = "Hello";

            /*you can give whatever you want for password. This is for testing purpose*/
            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[20];
            random.nextBytes(bytes);
            byte[] saltBytes = bytes;

            // Derive the key
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 65556, 256);
            SecretKey secretKey = factory.generateSecret(spec);
            SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

            //encrypting the word
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();
            ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] encryptedTextBytes = cipher.doFinal(pass.getBytes(StandardCharsets.UTF_8));
            //prepend salt and vi
            byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];

            System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
            System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
            System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);

            return new Base64().encodeToString(buffer);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @SuppressWarnings("static-access")
    public String decrypt(String encryptedText) throws Exception {
        String key = "Hello";
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //strip off the salt and iv
        ByteBuffer buffer = ByteBuffer.wrap(new Base64().decode(encryptedText));
        byte[] saltBytes = new byte[20];
        buffer.get(saltBytes, 0, saltBytes.length);
        byte[] ivBytes1 = new byte[cipher.getBlockSize()];
        buffer.get(ivBytes1, 0, ivBytes1.length);
        byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes1.length];

        buffer.get(encryptedTextBytes);

        // Deriving the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 65556, 256);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes1));
        byte[] decryptedTextBytes = null;

        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(decryptedTextBytes);
    }

    public static void main(String[] args) throws Exception {
        EncryptionUtil en = new EncryptionUtil();
        String encryptedWord = en.encrypt("MyNameIsMehdi");
        System.out.println("Encrypted word is : " + encryptedWord);
        System.out.println("Decrypted word is : " + en.decrypt(encryptedWord));
    }

}
