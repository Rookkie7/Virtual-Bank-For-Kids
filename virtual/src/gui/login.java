package gui;
import utill.read.LoginSystem;
import javax.swing.*;
import entity.Account;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public login() {
        setTitle("Login"); 
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 250)); 
        add(panel);
        placeComponents(panel);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(60, 179, 113);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        userLabel.setForeground(labelColor);
        userLabel.setBounds(30, 30, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(110, 30, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        passwordLabel.setForeground(labelColor); 
        passwordLabel.setBounds(30, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(110, 60, 160, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(255, 182, 193)); 
        loginButton.setBounds(90, 100, 110, 25);
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(135, 206, 250)); 
        registerButton.setBounds(90, 140, 110, 25);
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim(); // 获取用户名并去除首尾空白
                String password = new String(passwordField.getPassword()).trim(); // 获取密码并去除首尾空白

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(login.this, "Please enter your username and password", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 执行登录操作，这里可以根据需要进行处理，比如验证用户名密码等
		    LoginSystem loginSystem = new LoginSystem();
            Account result = LoginSystem.validateLogin(username, password);
                    if (result != null) {
                        JOptionPane.showMessageDialog(login.this, "Welcome", "Mention", JOptionPane.INFORMATION_MESSAGE);
                        // 登录成功后清空输入框内容
                        usernameField.setText("");
                        passwordField.setText("");
                        String idcode = result.getAccount();
                        String parentCode = result.getParentCode();
                        dispose(); // Close the login frame
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                new MainInterface(idcode, parentCode);
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(login.this, "Login failed", "Mention", JOptionPane.ERROR_MESSAGE);
			usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new register(); 
                // new login();
            }
        });
    }

    public static void main(String[] args) {
        new login();
    }
}
