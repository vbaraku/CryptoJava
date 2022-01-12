import java.io.File;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class SymCryptoMain {

	// Read "files" directory and ask user to encrypt/decrypt all files
	public static void main(String[] args) {

		// Get list of files in the directory
		File dir = new File("files");
		File[] filelist = dir.listFiles();

		try {
			// Instantiate the secret key generator
			KeyGenerator keygen = new KeyGenerator("AES", 16, "MySecr3tPassw0rd");

			// Show choice window for user
			int choice = -2;
			while (choice != -1) {
				String[] options = { "Encrypt All", "Decrypt All", "Exit" };
				choice = JOptionPane.showOptionDialog(null, "Select an option", "Options", 0,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				switch (choice) {

				// Encrypt all files option
				case 0:
					Arrays.asList(filelist).forEach(file -> {
						try {
							CryptoMethods.encryptFile(file, keygen.getCipher(), keygen.getSecretKey());
						} catch (Exception e) {
							System.err.println("Couldn't encrypt " + file.getName() + ": " + e.getMessage());
						}
					});
					System.out.println("File encryption finished");
					break;

				// Decrypt all files option
				case 1:
					Arrays.asList(filelist).forEach(file -> {
						try {
							CryptoMethods.decryptFile(file, keygen.getCipher(), keygen.getSecretKey());
						} catch (Exception e) {
							System.err.println("Couldn't decrypt " + file.getName() + ": " + e.getMessage());
						}
					});
					System.out.println("File decryption finished");
					break;

				// Exit application
				default:
					choice = -1;
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("Incorrect Algorithm Parameters: " + e.getMessage());
		}
	}
}
