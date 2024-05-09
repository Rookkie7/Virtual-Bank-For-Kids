package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskDialog extends JDialog implements ActionListener {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField deadlineField;
    private JTextField rewardField;
    private JButton okButton;
    private JButton cancelButton;
    private Task task;

    public TaskDialog(JFrame parent) {
        super(parent, "Set Task", true);
        setSize(600, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 20, 5);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField nameField = new JTextField(30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        JTextField descriptionField = new JTextField(30);
        descriptionField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(deadlineLabel, gbc);

        gbc.gridx = 1;
        JTextField deadlineField = new JTextField(30);
        deadlineField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(deadlineField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel rewardlineLabel = new JLabel("Reward:");
        rewardlineLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(rewardlineLabel, gbc);

        gbc.gridx = 1;
        JTextField rewardField = new JTextField(30);
        rewardField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(rewardField, gbc);


        okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 18));
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String deadline = deadlineField.getText();
            String reward = rewardField.getText();
            task = new Task(name, description, deadline, reward);
            dispose();
        } else if (e.getSource() == cancelButton) {
            task = null;
            dispose();
        }
    }

    public Task getTask() {
        return task;
    }
}
