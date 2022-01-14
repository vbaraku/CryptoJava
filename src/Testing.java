import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


//This is a class for testing purposes, if you want to mess around and alter the password, or the cipher
//.. this is the place to do it.

public class Testing{
    
    //helper method to get a sample file you want to test.
    public static String getTextInFile(File file) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String textInFile = "";
        
        while ((st = br.readLine()) != null) {
            textInFile = textInFile + st;
        }
        
        return textInFile;    
    }
    
    public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        
        try {
            KeyGenerator keygen = new KeyGenerator("AES", 16, "MySecr3tPassw0rd");
            
            File testFile = new File("src/TesterFile");
            
            String whatTextShouldBe = "This is a file for testing.";
            
            String textInFile = "";
            
       
            System.out.println(getTextInFile(testFile));    
            System.out.println(getTextInFile(testFile).equals(whatTextShouldBe));
            
            
            CryptoMethods.encryptFile(testFile, keygen.getCipher(), keygen.getSecretKey());
            System.out.println(getTextInFile(testFile));
            System.out.println(getTextInFile(testFile).equals(whatTextShouldBe));
            
            
            
            CryptoMethods.decryptFile(testFile, keygen.getCipher(), keygen.getSecretKey());
            System.out.println(getTextInFile(testFile));
            System.out.println(getTextInFile(testFile).equals(whatTextShouldBe));
            
            
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
