import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StringEncryptionDecryption extends JFrame implements ActionListener {
    private JTextField inputField, keyField, resultField;
    private JButton encryptButton, decryptButton;

    public StringEncryptionDecryption() {
        setTitle("String Encryption and Decryption");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(4, 2));

        inputField = new JTextField();
        keyField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);

        add(new JLabel("Input String:"));
        add(inputField);
        add(new JLabel("Key (Shift Value):"));
        add(keyField);
        add(new JLabel("Result:"));
        add(resultField);
        add(encryptButton);
        add(decryptButton);

        setVisible(true);
    }

    private String encryptString(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypted.append((char) ((c - base + key) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private String decryptString(String input, int key) {
        return encryptString(input, 26 - key); // Decrypting is the same as encrypting with (26 - key)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();
        String keyText = keyField.getText().trim();
        int key = 0;

        try {
            key = Integer.parseInt(keyText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid key! Please enter an integer value.");
            return;
        }

        if (e.getSource() == encryptButton) {
            String encryptedText = encryptString(input, key);
            resultField.setText(encryptedText);
        } else if (e.getSource() == decryptButton) {
            String decryptedText = decryptString(input, key);
            resultField.setText(decryptedText);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StringEncryptionDecryption());
    }
}