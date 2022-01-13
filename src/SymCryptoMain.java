import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

public class SymCryptoMain {

	// Read "files" directory and ask user to encrypt/decrypt all files
	public static void main(String[] args) {

		// Get list of files in the directory
		File dir = new File("files");
		File[] filelist = dir.listFiles();
		final JFrame frame = new JFrame("CRYPTOJAVA ENCRYPTION");
		final JFileChooser choose = new JFileChooser();

		try {
			// Instantiate the secret key generator
			String keyByUser;
			KeyGenerator keygen = new KeyGenerator("AES", 16, "MySecr3tPassw0rd");

			JButton btn1 = new JButton("Encrypt a file");
			btn1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int value = choose.showDialog(frame, "Encrypt");
					File file = choose.getSelectedFile();
					if (value == JFileChooser.APPROVE_OPTION) {
						try {
							CryptoMethods.encryptFile(file, keygen.getCipher(), keygen.getSecretKey());
						} catch (Exception exeption) {
							JOptionPane.showMessageDialog(frame, file.getName() + " File encryption has failed",
									"Encryption Unsucessfull", JOptionPane.ERROR_MESSAGE);

						}
					}
					;
					JOptionPane.showMessageDialog(frame, "File encryption finished", "Encryption Sucessfull",
							JOptionPane.INFORMATION_MESSAGE);

				}
			});

			JButton btn2 = new JButton("Decrypt a file");
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int retVal = choose.showDialog(frame, "Decrypt");
					File file = choose.getSelectedFile();
					if (retVal == JFileChooser.APPROVE_OPTION) {
						try {
							CryptoMethods.decryptFile(file, keygen.getCipher(), keygen.getSecretKey());
						} catch (Exception exeption) {
							JOptionPane.showMessageDialog(frame,
									"Couldn't decrypt " + file.getName() + ": " + exeption.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					;
					JOptionPane.showMessageDialog(frame, file.getName() + " Has been Decrypted", "Encription Message",
							JOptionPane.INFORMATION_MESSAGE);
				}

			});

			//Images
			ImageIcon encrypticon = new ImageIcon("Encrypt.png");
			ImageIcon decrypticon = new ImageIcon("Decrypt.png");
			
			
			// GUI PROPERTIES
			Container pane = frame.getContentPane();
			
			JPanel P = new JPanel();
			P.setBackground(new Color(52, 73, 94));
			JLabel welcome = new JLabel("Welcome to the CryptoJava");
			JLabel protecc = new JLabel("Program Designed to protect your files");
			JLabel select = new JLabel("I Want To:");
			
			welcome.setFont(new Font("Monospaced", Font.BOLD, 26));
			welcome.setForeground(Color.WHITE);
			protecc.setFont(new Font("Monospaced", Font.PLAIN, 22));
			protecc.setForeground(Color.WHITE);
			btn1.setFont(new Font("Dialog", Font.PLAIN, 24));
			btn2.setFont(new Font("Dialog", Font.PLAIN, 24));

			Dimension size = welcome.getPreferredSize();

			// Upperlabels
			welcome.setBounds(125, 50, size.width + 600, size.height);
			protecc.setBounds(70, 80, size.width + 600, size.height);
			select.setBounds(145, 280,size.width + 600, size.height);

			// Button Positions
			btn1.setBounds(125, 300, 185, 65);
			btn1.setIcon(encrypticon);
			btn1.setOpaque(false);
			btn1.setForeground(Color.white);
			btn1.setBorder(new RoundedBorder(20));
			btn1.setBackground(Color.white);

			btn2.setBounds(350, 300, 200, 65);
			btn2.setIcon(decrypticon);
			btn2.setOpaque(false);
			btn2.setForeground(Color.white);
			btn2.setBorder(new RoundedBorder(20));
			btn2.setBackground(Color.white);


			
			P.setLayout(null);
			P.add(welcome);
			P.add(protecc);

			P.add(btn1);
			P.add(btn2);

			pane.add(P);

//	        pane.add(btn1);
//	        pane.add(btn2);

			frame.setSize(640, 480);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} catch (Exception e) {
			System.err.println("Incorrect Algorithm Parameters: " + e.getMessage());
		}

	}
}

class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
