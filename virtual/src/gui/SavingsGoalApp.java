package gui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SavingsGoalApp extends JFrame {
    private JTextField goalNameField;
    private JTextField targetAmountField;
    private JButton createGoalButton;
    private JButton viewHistoryButton;
    private JButton modifyGoalButton;

    private String idcode;
    public String GOALS_FILE = null;

    public SavingsGoalApp(String idcode, String parentCode) {
        this.idcode = idcode;
        this.GOALS_FILE = "data/" + idcode + "/GoalList.txt";
        setTitle("Savings Goals App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents(idcode, parentCode);
    }

    private void initComponents(String idcode, String parentCode) {
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

        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(modifyGoalButton);

        add(inputPanel, BorderLayout.NORTH);
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
                new HistoryFrame(SavingsGoalApp.this, idcode).setVisible(true);
            }
        });

        modifyGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoryFrame(SavingsGoalApp.this, true, idcode).setVisible(true);
            }
        });
    }

    private void createGoal() {
        String name = goalNameField.getText();
        double targetAmount = Double.parseDouble(targetAmountField.getText());
        String currentDate = getCurrentDate();

        SavingsGoal goal = new SavingsGoal(name, targetAmount, 0.0, currentDate);
        saveGoalToFile(goal);
    }

    private void saveGoalToFile(SavingsGoal goal) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GOALS_FILE, true))) {
            writer.println(goal.getName() + "," + goal.getTargetAmount() + "," + goal.getCurrentAmount() + "," + goal.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private boolean isValidInput() {
        String name = goalNameField.getText();
        String amount = targetAmountField.getText();

        String regex = "^[a-zA-Z0-9]+$";

        return name.matches(regex) && amount.matches(regex);
    }

    public static void main(String idcode, String parentCode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SavingsGoalApp(idcode, parentCode).setVisible(true);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public String getDate() {
        return date;
    }
}

class HistoryFrame extends JFrame {
    private JList<String> goalsList;
    private DefaultListModel<String> listModel;
    private JButton backButton;
    private JButton modifyButton;
    private JButton deleteButton;

    private ArrayList<SavingsGoal> goals;

    private String idcode;
    public String GOALS_FILE = null;

    public HistoryFrame(JFrame parent, String idcode) {
        this.idcode = idcode;
        this.GOALS_FILE = "data/" + idcode + "/GoalList.txt";
        setTitle("Goal History");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        initComponents();
        loadGoalsFromFile();
    }

    public HistoryFrame(JFrame parent, boolean modifyMode,String idcode) {
        this(parent,idcode);
        setModifyMode(modifyMode);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        Font font = new Font("Arial", Font.PLAIN, 16);

        listModel = new DefaultListModel<>();
        goalsList = new JList<>(listModel);
        goalsList.setFont(font);
        goalsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        goalsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = goalsList.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        SavingsGoal goal = goals.get(index);
                        if (modifyButton != null) {
                            modifyGoal(goal);
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(goalsList);

        backButton = new JButton("Back");
        backButton.setFont(font);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void setModifyMode(boolean modifyMode) {
        if (modifyMode) {
            Font font = new Font("Arial", Font.PLAIN, 16);
            modifyButton = new JButton("Modify");
            modifyButton.setFont(font);

            deleteButton = new JButton("Delete");
            deleteButton.setFont(font);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(modifyButton);
            buttonPanel.add(deleteButton);

            add(buttonPanel, BorderLayout.SOUTH);

            modifyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = goalsList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        SavingsGoal goal = goals.get(selectedIndex);
                        modifyGoal(goal);
                    } else {
                        JOptionPane.showMessageDialog(HistoryFrame.this, "Please select a goal to modify.");
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = goalsList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        SavingsGoal goal = goals.get(selectedIndex);
                        deleteGoal(goal);
                    } else {
                        JOptionPane.showMessageDialog(HistoryFrame.this, "Please select a goal to delete.");
                    }
                }
            });
        }
    }

    private void loadGoalsFromFile() {
        goals = new ArrayList<>();
        listModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(GOALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    double targetAmount = Double.parseDouble(parts[1]);
                    double currentAmount = Double.parseDouble(parts[2]);
                    String date = parts[3];

                    SavingsGoal goal = new SavingsGoal(name, targetAmount, currentAmount, date);
                    goals.add(goal);

                    listModel.addElement("Name: " + goal.getName() + ", Target: $" + goal.getTargetAmount() + ", Current: $" + goal.getCurrentAmount() + ", Date: " + goal.getDate());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyGoal(SavingsGoal goal) {
        JTextField nameField = new JTextField(goal.getName());
        JTextField amountField = new JTextField(String.valueOf(goal.getTargetAmount()));

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Goal Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Target Amount:"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modify Goal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newName = nameField.getText();
            double newAmount = Double.parseDouble(amountField.getText());

            // Update the goal
            goal.setName(newName);
            goal.setTargetAmount(newAmount);
            updateGoalsInFile();
            // Update the display
            loadGoalsFromFile();

            JOptionPane.showMessageDialog(this, "Goal modified successfully!");
        }
    }

    private void deleteGoal(SavingsGoal goal) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this goal?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            goals.remove(goal);
            updateGoalsInFile();
            loadGoalsFromFile(); // 刷新页面
            JOptionPane.showMessageDialog(this, "Goal deleted successfully!");
        }
    }

    private void updateGoalsInFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GOALS_FILE))) {
            for (SavingsGoal goal : goals) {
                writer.println(goal.getName() + "," + goal.getTargetAmount() + "," + goal.getCurrentAmount() + "," + goal.getDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
