package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;

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
        panel.setBackground(new Color(230, 230, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 20, 5);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        nameField = new JTextField(30);
        nameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        descriptionField = new JTextField(30);
        descriptionField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        panel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel deadlineLabel = new JLabel("Deadline:");
        deadlineLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(deadlineLabel, gbc);

        gbc.gridx = 1;
        deadlineField = new JTextField(30);
        deadlineField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        panel.add(deadlineField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel rewardlineLabel = new JLabel("Reward:");
        rewardlineLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        panel.add(rewardlineLabel, gbc);

        gbc.gridx = 1;
        rewardField = new JTextField(30);
        rewardField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        panel.add(rewardField, gbc);


        okButton = new JButton("OK");
        okButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
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
            if (name.isEmpty() || description.isEmpty() || deadline.isEmpty() || reward.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The content cannot be empty");
                return; // Terminate the method
            }
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
