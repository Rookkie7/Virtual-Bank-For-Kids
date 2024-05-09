package gui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsGoalApp extends JFrame {
    private JTextField goalNameField;
    private JTextField targetAmountField;
    private JButton createGoalButton;
    private JTextArea goalsTextArea;
    private JButton viewHistoryButton;
    private JButton modifyGoalButton;
    private JButton deleteGoalButton;

    private static final String GOALS_FILE = "savings_goals.txt";

    public SavingsGoalApp() {
        setTitle("Savings Goals App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadGoalsFromFile();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        Font font = new Font("Arial", Font.PLAIN, 16);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        goalNameField = new JTextField();
        goalNameField.setFont(font);
        targetAmountField = new JTextField();
        targetAmountField.setFont(font);
        createGoalButton = new JButton("Create Goal");
        createGoalButton.setFont(font);

        inputPanel.add(new JLabel("Goal Name:"));
        inputPanel.add(goalNameField);
        inputPanel.add(new JLabel("Target Amount:"));
        inputPanel.add(targetAmountField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(createGoalButton);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        viewHistoryButton = new JButton("View History");
        viewHistoryButton.setFont(font);
        modifyGoalButton = new JButton("Modify Goal");
        modifyGoalButton.setFont(font);
        deleteGoalButton = new JButton("Delete Goal");
        deleteGoalButton.setFont(font);

        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(modifyGoalButton);
        buttonPanel.add(deleteGoalButton);

        goalsTextArea = new JTextArea();
        goalsTextArea.setFont(font);
        goalsTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(goalsTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidInput()) {
                    createGoal();
                } else {
                    JOptionPane.showMessageDialog(SavingsGoalApp.this, "Invalid input! Please enter only letters and numbers.");
                }
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHistory();
            }
        });

        modifyGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyGoal();
            }
        });

        deleteGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGoal();
            }
        });
    }

    private void createGoal() {
        String name = goalNameField.getText();
        double targetAmount = Double.parseDouble(targetAmountField.getText());
        String currentDate = getCurrentDate();

        SavingsGoal goal = new SavingsGoal(name, targetAmount, 0.0, currentDate);
        saveGoalToFile(goal);
        displayGoals();
    }

    private void saveGoalToFile(SavingsGoal goal) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GOALS_FILE, true))) {
            writer.println(goal.getName() + "," + goal.getTargetAmount() + "," + goal.getCurrentAmount() + "," + goal.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGoalsFromFile() {
        File file = new File(GOALS_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(GOALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Make sure the line has 4 parts
                    String name = parts[0];
                    double targetAmount = Double.parseDouble(parts[1]);
                    double currentAmount = Double.parseDouble(parts[2]);
                    String date = parts[3];

                    SavingsGoal goal = new SavingsGoal(name, targetAmount, currentAmount, date);
                    displayGoal(goal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayGoals() {
        goalsTextArea.setText(""); // Clear existing text
        loadGoalsFromFile(); // Reload goals and display
    }

    private SavingsGoal selectedGoal; // 新增一个成员变量来存储当前选定的目标

// 在 displayGoal 方法中为每个目标添加点击事件监听器，以便选定目标
private void displayGoal(SavingsGoal goal) {
    JButton selectButton = new JButton("Select"); // 添加一个“选择”按钮
    selectButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedGoal = goal; // 选定目标
            goalNameField.setText(goal.getName()); // 在输入字段中显示目标名称
            targetAmountField.setText(Double.toString(goal.getTargetAmount())); // 在输入字段中显示目标金额
        }
    });
    goalsTextArea.append("Name: " + goal.getName() + ", Target: $" + goal.getTargetAmount() + ", Current: $" + goal.getCurrentAmount() + ", Date: " + goal.getDate());
    goalsTextArea.append("\n");
    goalsTextArea.add(selectButton); // 将“选择”按钮添加到文本区域中
}

// 修改目标方法
private void modifyGoal() {
    if (selectedGoal != null) {
        String newName = goalNameField.getText(); // 获取新的目标名称
        double newTargetAmount = Double.parseDouble(targetAmountField.getText()); // 获取新的目标金额

        // 更新选定目标的信息
        selectedGoal.setName(newName);
        selectedGoal.setTargetAmount(newTargetAmount);

        // 重新加载显示所有目标
        displayGoals();

        // 清空输入字段和选定目标
        goalNameField.setText("");
        targetAmountField.setText("");
        selectedGoal = null;
    } else {
        JOptionPane.showMessageDialog(this, "Please select a goal to modify.");
    }
}


    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // Method to check if input is valid
    private boolean isValidInput() {
        String name = goalNameField.getText();
        String amount = targetAmountField.getText();

        // Regular expression to allow only letters and numbers
        String regex = "^[a-zA-Z0-9]+$";

        return name.matches(regex) && amount.matches(regex);
    }

    // Method to display history goals
    private void displayHistory() {
        goalsTextArea.setText(""); // Clear existing text
        try (BufferedReader reader = new BufferedReader(new FileReader(GOALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Make sure the line has 4 parts
                    String name = parts[0];
                    double targetAmount = Double.parseDouble(parts[1]);
                    double currentAmount = Double.parseDouble(parts[2]);
                    String date = parts[3];

                    SavingsGoal goal = new SavingsGoal(name, targetAmount, currentAmount, date);
                    displayGoal(goal);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    

    // Method to delete goal
    private void deleteGoal() {
        // Add functionality to delete goal
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SavingsGoalApp().setVisible(true);
            }
        });
    }
}

class SavingsGoal {
    private String name;
    private double targetAmount;
    private double currentAmount;
    private String date;

    public SavingsGoal(String name, double targetAmount, double currentAmount, String date) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.date = date;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public String getDate() {
        return date;
    }
}
