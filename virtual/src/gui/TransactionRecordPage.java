package gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TransactionRecordPage extends JFrame implements ActionListener {

    private JTextArea transactionArea;
    private JLabel userLabel;



    public TransactionRecordPage() {
        setTitle("Children's Bank Transaction Record");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示窗口

        // 创建顶部面板
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(207, 141, 255)); // 设置顶部面板背景色

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent windowEvent){
                new MainInterface().setVisible(true);;
                setVisible(false);
            }
        });
        // 创建用户头像标签，并设置图片
        JLabel avatarLabel = new JLabel();
        try {
            BufferedImage avatarImage = ImageIO.read(new File("/Users/syh/Downloads/这不好笑.jpg")); // 替换 "avatar.jpg" 为用户头像文件路径
            Image scaledAvatarImage = avatarImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH); // 缩放图片
            avatarLabel.setIcon(new ImageIcon(scaledAvatarImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 为用户头像和用户名创建边框装饰
        Border border = new LineBorder(new Color(255, 213, 0), 4); // 黑色边框
        avatarLabel.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
        userLabel = new JLabel("ICE TEA");
        userLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // 设置字体和大小
        userLabel.setForeground(new Color(157, 71, 0)); // 设置字体颜色为黑色
        userLabel.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));

        // 添加用户头像和名称到顶部面板
        topPanel.add(avatarLabel, BorderLayout.WEST);
        topPanel.add(userLabel, BorderLayout.CENTER);

        // 创建交易记录文本区域
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        transactionArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); // 设置字体和大小
        transactionArea.setLineWrap(true); // 自动换行
        transactionArea.setBackground(new Color(230, 230, 255)); // 设置文字背景色为淡紫色
        transactionArea.setBorder(null); // 设置边框为空边框
        // 设置文字颜色为RGB模式
        transactionArea.setForeground(new Color(168, 136, 0)); // 设置文字颜色为黑色





        // 创建滚动面板，并将文本区域添加到其中
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // 设置边距

        // 创建显示交易记录的按钮
        JButton showTransactionsButton = new JButton("Show Transactions");
        showTransactionsButton.addActionListener(this);
        showTransactionsButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // 设置按钮字体和大小
        showTransactionsButton.setBackground(new Color(255, 255, 255)); // 设置按钮背景颜色
        showTransactionsButton.setForeground(new Color(4, 142, 255, 242)); // 设置按钮前景颜色为白色

        // 创建一个面板来容纳按钮，并设置背景颜色
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(192, 192, 192)); // 设置背景颜色为灰色
        buttonPanel.add(showTransactionsButton);

        // 创建底部面板，并添加拉花装饰
        JPanel bottomPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(255, 192, 203)); // 设置底部面板背景色为粉红色
                g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight()); // 背景填充
                g2d.setColor(new Color(0, 0, 255)); // 设置拉花颜色为蓝色
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(20, getHeight() / 4, getWidth() - 20, getHeight() / 4); // 绘制拉花装饰线
                g2d.dispose();
            }
        };

        // 添加按钮到底部面板
        bottomPanel.add(showTransactionsButton, BorderLayout.NORTH);

        // 添加组件到窗口中
        getContentPane().setLayout(new BorderLayout()); // 设置窗口布局为边界布局
        getContentPane().add(topPanel, BorderLayout.NORTH); // 将顶部面板添加到北部
        getContentPane().add(scrollPane, BorderLayout.CENTER); // 将文本区域添加到中间
        getContentPane().add(bottomPanel, BorderLayout.SOUTH); // 将底部面板添加到南部
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Show Transactions")) {
            // 模拟从数据库或文件中获取交易记录，并显示在文本区域中
            String transactions = "2022-04-10: Deposit $100.00\n" +
                    "2022-04-11: Withdrawal $50.00\n" +
                    "2022-04-12: Transfer $20.00 to friend\n" +
                    "2022-04-13: Deposit $200.00\n" +
                    "2022-04-14: Withdrawal $30.00\n" +
                    "2022-04-15: Transfer $10.00 to family\n" +
                    "2022-04-16: Deposit $150.00\n" +
                    "2022-04-17: Withdrawal $20.00\n" +
                    "2022-04-18: Deposit $300.00\n" +
                    "2022-04-19: Withdrawal $40.00\n" +
                    "2022-04-20: Transfer $15.00 to colleague\n" +
                    "2022-04-21: Deposit $250.00\n" +
                    "2022-04-22: Withdrawal $25.00\n" +
                    "2022-04-23: Transfer $50.00 to friend\n" +
                    "2022-04-24: Deposit $180.00\n" +
                    "2022-04-25: Withdrawal $60.00\n" +
                    "2022-04-26: Transfer $30.00 to family\n" +
                    "2022-04-27: Deposit $220.00\n" +
                    "2022-04-28: Withdrawal $10.00\n" +
                    "2022-04-29: Transfer $25.00 to colleague";
            transactionArea.setText(transactions);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TransactionRecordPage recordPage = new TransactionRecordPage();
            recordPage.setVisible(true);
        });
    }
}