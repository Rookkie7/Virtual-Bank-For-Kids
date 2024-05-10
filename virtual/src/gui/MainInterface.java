package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {
    public MainInterface(String idcode, String parentCode) {
        setTitle("Banking Application");
        setLayout(new BorderLayout());

        // 创建顶部文字面板
        JPanel topTextPanel = createTopTextPanel("Virtual Bank Application for Kids", "Group 15");

        // 创建左侧按钮面板
        JPanel ButtonPanel = createButtonPanel("Deposit/Withdrawal", "Tasks","Transactions", "Savings Goals", "Quit","Sign out",idcode, parentCode);

        // 创建图片面板
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        imagePanel.setBackground(new Color(230, 230, 255));  // 设置整体边距
        ImageIcon imageIcon = new ImageIcon("img/MainInterface.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.WEST);

        // 将面板添加到窗口中
        add(topTextPanel, BorderLayout.NORTH);
        add(ButtonPanel, BorderLayout.EAST);
        // add(balancePanel, BorderLayout.EAST);
        add(imagePanel, BorderLayout.WEST);

        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
private JPanel createTopTextPanel(String text1, String text2) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // 设置整体边距
    panel.setBackground(new Color(207, 141, 255));

    JLabel label1 = new JLabel(text1);
    label1.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));
    panel.add(label1);

    JLabel label2 = new JLabel(text2);
    label2.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));
    panel.add(label2);

    return panel;
}

private JPanel createButtonPanel(String label1, String label2, String label3, String label4, String label5, String label6,String idcode, String parentCode ) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(8, 1,10,10)); // Set GridLayout with 3 rows and 1 column
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
    panel.setBackground(new Color(255, 192, 203));

    JPanel balancePanel = new JPanel();
    balancePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    balancePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));  // 设置整体边距
    JLabel balanceLabel = new JLabel("Balance Tracking =\n $10,000");
    balanceLabel.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));

    panel.add(balanceLabel);

    JButton button1 = createButton(label1);
    panel.add(button1);

    JButton button2 = createButton(label2);
    panel.add(button2);

    JButton button3 = createButton(label3);
    panel.add(button3);

    JButton button4 = createButton(label4);
    panel.add(button4);

    JButton button5 = createButton(label5);
    panel.add(button5);

    JButton button6 = createButton(label6);
    panel.add(button6);

    button1.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            SwingUtilities.invokeLater(() -> new BankAccountGUI(idcode).setVisible(true));//打开页面2，
        // setVisible(false);//关闭页面1
        }
    });
    button2.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new TaskListGUI( idcode, parentCode);//打开页面2，
        // setVisible(false);//关闭页面1
        }
    });
    button3.addActionListener(new ActionListener(){
    @Override
        public void actionPerformed(ActionEvent e){
            new TransactionRecordPage( idcode).setVisible(true);//打开页面2，
        // setVisible(false);//关闭页面1
        }
    });
    button4.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new SavingsGoalApp(idcode, parentCode).setVisible(true);//打开页面2，
        // setVisible(false);//关闭页面1
        }
    });
    button5.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
        // setVisible(false);//关闭页面1
        System.exit(0);
        }
    });
    button6.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new login();//打开页面2，
        // setVisible(false);//关闭页面1
        }
    });
    return panel;
}

private JButton createButton(String label) {
    JButton button = new JButton(label);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setSize(200,100);
    button.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 20));
    // button.setshape(new Rectangle(20,20));
    // button.setBorder(BorderFactory.createRoundBorder(10)); // 设置按钮边缘圆滑化
    return button;
}

public void handleOtherInterfaceClosed() {
    setVisible(true); // 将主界面设置为可见
}

public static void main(String idcode, String parentCode) {
        new MainInterface(idcode, parentCode);

}
}