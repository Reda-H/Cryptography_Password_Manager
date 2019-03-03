package Classes;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class AES {
	private static final String ALGO = "AES";
	private byte[] KeyValue;
	
	public AES(String Key) {
		KeyValue = Key.getBytes();
	}
	
	public String encrypt(String Data) throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue; 
	}
	
	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	
	private Key generateKey() {
		Key key = new SecretKeySpec(KeyValue, ALGO);
		return key;
	}

}
