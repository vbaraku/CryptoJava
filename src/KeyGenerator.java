import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyGenerator {
	private Cipher cipher;
	private SecretKey secretKey;

	// Constructor
	public KeyGenerator(String algorithm, int keysize, String secret)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException {
		super();
		cipher = Cipher.getInstance(algorithm);
		secretKey = new SecretKeySpec(fixSecretLength(secret, keysize), algorithm);
	}

	// Getters
	public Cipher getCipher() {
		return cipher;
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	// Helper Function: make sure specified password has proper length
	private byte[] fixSecretLength(String s, int length) throws UnsupportedEncodingException {
		if (s.length() < length) {
			int missingLength = length - s.length();
			for (int i = 0; i < missingLength; i++) {
				s += "!";
			}
		}
		return s.substring(0, length).getBytes("UTF-8");
	}

//	// Helper Function: write content to desired file path
//	private void writeToFile(String path, byte[] content) throws IOException {
//		File f = new File(path);
//		f.getParentFile().mkdirs();
//		FileOutputStream fos = new FileOutputStream(f);
//		fos.write(content);
//		fos.flush();
//		fos.close();
//	}

}
