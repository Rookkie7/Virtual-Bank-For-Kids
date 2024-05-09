package gui;
import utill.write.RegisterSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField codewordField;

    public register() {
        setTitle("Register"); // 修改标题为"Register"
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 250)); // 修改面板背景色为淡紫色
        add(panel);
        placeComponents(panel);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 修改字体样式和颜色
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
        passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // 设置字体样式
        passwordLabel.setForeground(labelColor); // 设置字体颜色
        passwordLabel.setBounds(30, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(110, 60, 160, 25);
        panel.add(passwordField);

	JLabel codeLabel = new JLabel("Parent Code:");
        codeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // 设置字体样式
        codeLabel.setForeground(labelColor); // 设置字体颜色
        codeLabel.setBounds(30, 100, 130, 25);
        panel.add(codeLabel);

        codewordField = new JPasswordField(20);
        codewordField.setBounds(165, 100, 80, 25);
        panel.add(codewordField);


        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(135, 206, 250)); // 修改按钮背景色为天蓝色
        registerButton.setBounds(90, 140, 110, 35);
	registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        panel.add(registerButton);
	registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String parentCode = new String(codewordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty() || parentCode.isEmpty()) {
                    JOptionPane.showMessageDialog(register.this, "Please enter your username，password and Parent Code", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 调用 RegisterSystem 类处理注册逻辑
                    if (RegisterSystem.register(username, password, parentCode)) {
                        JOptionPane.showMessageDialog(register.this, "Welcome", "Mention", JOptionPane.INFORMATION_MESSAGE);
                        // 注册成功后清空输入框内容
                        usernameField.setText("");
                        passwordField.setText("");
                        codewordField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(register.this, "Registration failed, username already exists", "Mention", JOptionPane.ERROR_MESSAGE);
			usernameField.setText("");
                        passwordField.setText("");
                        codewordField.setText("");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new register();
    }
}
