package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMPage extends JFrame {
    private JLabel balanceLabel;
    private JTextField amountField;

    public ATMPage() {
        setTitle("ATM System");
        setSize(300, 200);
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
        Color labelColor1 = new Color(60, 179, 113);
	Color labelColor2 = new Color(255, 182, 193);
	Color labelColor3 = new Color(135, 206, 250);

        JButton withdrawButton = new JButton("Withdraw");
	withdrawButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        withdrawButton.setForeground(labelColor1);
        withdrawButton.setBounds(50, 20, 180, 25);
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
	depositButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        depositButton.setForeground(labelColor2);
        depositButton.setBounds(50, 50, 180, 25);
        panel.add(depositButton);

        JButton balanceButton = new JButton("Balance inquiry");
	balanceButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        balanceButton.setForeground(labelColor3);
        balanceButton.setBounds(50, 80, 180, 25);
        panel.add(balanceButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WithdrawPage();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DepositPage();
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BalancePage();
            }
        });
    }

    public static void main(String[] args) {
        new ATMPage();
    }
}

class WithdrawPage extends JFrame {
    private JLabel selectLabel;
    private JTextField amountField;

    public WithdrawPage() {
        setTitle("Withdrawal");
        setSize(300, 200);
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
        Color labelColor1 = new Color(60, 179, 113);
	Color labelColor2 = new Color(255, 182, 193);
	Color labelColor3 = new Color(135, 206, 250);

        selectLabel = new JLabel("Select withdrawal amount:");
	selectLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        selectLabel.setForeground(labelColor3);
        selectLabel.setBounds(50, 20, 200, 25);
        panel.add(selectLabel);

        JComboBox<String> amountComboBox = new JComboBox<>(new String[]{"100", "200", "500", "1000", "Others"});
	amountComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        amountComboBox.setForeground(labelColor2);
        amountComboBox.setBounds(50, 50, 180, 25);
        panel.add(amountComboBox);

        amountField = new JTextField();
	amountField.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        amountField.setForeground(labelColor2);
        amountField.setBounds(50, 80, 180, 25);
        amountField.setVisible(false);
        panel.add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
	withdrawButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        withdrawButton.setForeground(labelColor1);
        withdrawButton.setBounds(50, 110, 180, 25);
        panel.add(withdrawButton);

        JButton backButton = new JButton("Back");
	backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        backButton.setForeground(labelColor3);
        backButton.setBounds(50, 140, 180, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ATMPage();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the amout
                String selectedAmount = (String) amountComboBox.getSelectedItem();
                if (selectedAmount.equals("Others")) {
                    selectedAmount = amountField.getText(); 
                }

                // Need the bussiness logic,can't work now
                System.out.println("Withdrawal amout：" + selectedAmount);

                // To balance inquiry
                dispose();
                new BalancePage();
            }
        });

        amountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amountComboBox.getSelectedItem().equals("Others")) {
                    amountField.setVisible(true);
                } else {
                    amountField.setVisible(false);
                }
            }
        });
    }
}

class DepositPage extends JFrame {
    public DepositPage() {
        setTitle("Deposit");
        setSize(300, 200);
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
        Color labelColor1 = new Color(60, 179, 113);
	Color labelColor2 = new Color(255, 182, 193);
	Color labelColor3 = new Color(135, 206, 250);

        JLabel depositLabel = new JLabel("Deposit:");
	depositLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        depositLabel.setForeground(labelColor1);
        depositLabel.setBounds(50, 30, 200, 25);
        panel.add(depositLabel);

        JTextField amountField = new JTextField();
	amountField.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        amountField.setForeground(labelColor1);
        amountField.setBounds(50, 60, 180, 25);
        panel.add(amountField);

        JButton depositButton = new JButton("Deposit");
	depositButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        depositButton.setForeground(labelColor2);
        depositButton.setBounds(50, 90, 180, 25);
        panel.add(depositButton);

        JButton backButton = new JButton("Back");
	backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        backButton.setForeground(labelColor3);
        backButton.setBounds(50, 120, 180, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ATMPage();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositAmount = amountField.getText();
                System.out.println("Deposit amout：" + depositAmount);

                JOptionPane.showMessageDialog(null, "Done");
                dispose();
                new BalancePage();
            }
        });
    }
}

class BalancePage extends JFrame {
    private JLabel balanceLabel;

    public BalancePage() {
        setTitle("Balance inquiry");
        setSize(300, 200);
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
        Color labelColor1 = new Color(60, 179, 113);
	Color labelColor2 = new Color(255, 182, 193);
	Color labelColor3 = new Color(135, 206, 250);

        balanceLabel = new JLabel("Balance: 1000");
	balanceLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        balanceLabel.setForeground(labelColor3);
        balanceLabel.setBounds(50, 30, 200, 25);
        panel.add(balanceLabel);

        JButton backButton = new JButton("Back");
	backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); 
        backButton.setForeground(labelColor2);
        backButton.setBounds(100, 80, 100, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ATMPage();
            }
        });
    }
}
