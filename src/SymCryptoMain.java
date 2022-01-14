import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

public class SymCryptoMain {

	// Read "files" directory and ask user to encrypt/decrypt all files
	public static void main(String[] args) {

		//Initialize the JFrame and the fileChooser
		final JFrame frame = new JFrame("CRYPTOJAVA ENCRYPTION");
		final JFileChooser choose = new JFileChooser();

		try {
			// Instantiate the secret key generator
			KeyGenerator keygen = new KeyGenerator("AES", 16, "MySecr3tPassw0rd");

			JButton btn1 = new JButton("Encrypt a file");
			btn1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int response = choose.showDialog(frame, "Encrypt");
					File file = choose.getSelectedFile();
					if (response == JFileChooser.APPROVE_OPTION) {
						try {
							CryptoMethods.encryptFile(file, keygen.getCipher(), keygen.getSecretKey());
						} catch (Exception exeption) {
							JOptionPane.showMessageDialog(frame, file.getName() + " File encryption has failed",
									"Encryption Unsucessfull", JOptionPane.ERROR_MESSAGE);
						}

						JOptionPane.showMessageDialog(frame, file.getName() + " Has Been Encrypted", "Encryption Sucessfull",
								JOptionPane.INFORMATION_MESSAGE, new ImageIcon(SymCryptoMain.class.getResource("check.png")));
					} else if (response == JFileChooser.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(frame, "Canceled Encryption", "Cancel",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			});

			JButton btn2 = new JButton("Decrypt a file");
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int response = choose.showDialog(frame, "Decrypt");
					File file = choose.getSelectedFile();
					if (response == JFileChooser.APPROVE_OPTION) {
						try {
							CryptoMethods.decryptFile(file, keygen.getCipher(), keygen.getSecretKey());
						} catch (Exception exeption) {
							JOptionPane.showMessageDialog(frame,
									"Couldn't decrypt " + file.getName() + ": " + exeption.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(frame, file.getName() + " Has Been Decrypted!",
								"Encription Message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(SymCryptoMain.class.getResource("check.png")));
					} else if (response == JFileChooser.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(frame, "Canceled Decryption", "Cancel",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

			});			

			// GUI PROPERTIES
			Container pane = frame.getContentPane();
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			JPanel P = new JPanel();
			P.setBackground(new Color(31, 71, 133));
			
			
			JLabel welcome = new JLabel("Welcome to the CryptoJava");
			JLabel protecc = new JLabel("Program Designed to protect your files");
			JLabel select = new JLabel("I Want To:");
			
			//Images
			ImageIcon icon1 = new ImageIcon(SymCryptoMain.class.getResource("encrypted.png"));
			ImageIcon icon2 = new ImageIcon(SymCryptoMain.class.getResource("key.png"));
			JLabel home = new JLabel();
			home.setIcon(new ImageIcon(SymCryptoMain.class.getResource("home.png")));
			
			welcome.setFont(new Font("Monospaced", Font.BOLD, 26));
			welcome.setForeground(Color.WHITE);
			protecc.setFont(new Font("Monospaced", Font.PLAIN, 22));
			protecc.setForeground(Color.WHITE);
			select.setFont(new Font("Monospaced", Font.BOLD, 23));
			select.setForeground(Color.WHITE);
			btn1.setFont(new Font("Dialog", Font.PLAIN, 24));
			btn2.setFont(new Font("Dialog", Font.PLAIN, 24));
			

			Dimension size = welcome.getPreferredSize();

			// Upperlabels
			welcome.setBounds(125, 40, size.width + 600, size.height);
			protecc.setBounds(70, 70, size.width + 600, size.height);
			select.setBounds(245, 250, size.width + 600, size.height);
			home.setBounds(250, 120, 128, 128);

			// Button Positions
			btn1.setBounds(65, 330, 240, 65);
			btn1.setOpaque(false);
			btn1.setForeground(Color.white);
			btn1.setHorizontalTextPosition(JButton.RIGHT);
			btn1.setIcon(icon1);
			btn1.setBorder(new RoundedBorder(20));
			btn1.setBackground(Color.white);

			btn2.setBounds(325, 330, 240, 65);
			btn2.setOpaque(false);
			btn2.setIcon(icon2);
			btn2.setForeground(Color.white);
			btn2.setBorder(new RoundedBorder(20));
			btn2.setBackground(Color.white);

			P.setLayout(null);
			P.add(welcome);
			P.add(protecc);
			P.add(select);
			P.add(home);
			
			

			P.add(btn1);
			P.add(btn2);

			pane.add(P);

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
		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}
}
