package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entity.*;
import utill.read.TaskReader;
import utill.write.TaskWriter;

public class TaskListGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JButton setTaskButton;
    private JButton deleteTaskButton;
    private ArrayList<Task> tasks;
    private String parentCode;
    private String idcode;

    public TaskListGUI(String idcode, String parentCode) {

        this.parentCode = parentCode;
        this.idcode = idcode;

        

        setTitle("Tasks");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        titleLabel = new JLabel("Tasks");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);//标签调为不透明（显色
        titleLabel.setBackground(new Color(207, 141, 255));//背景颜色

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setCellRenderer(new TaskListRenderer());
        taskList.setBackground(new Color(230, 230, 255));//背景颜色

        JScrollPane listScrollPane = new JScrollPane(taskList);

        setTaskButton = new JButton("Set Task");
        setTaskButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30)); // 设置按钮字体大小为20
        setTaskButton.addActionListener(this);

        deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30)); // 设置按钮字体大小为20
        deleteTaskButton.addActionListener(this);

        tasks = new ArrayList<>();
        ArrayList<Task> savedTasks = TaskReader.readTasksFromFile(idcode);
        for (Task task : savedTasks) {
            tasks.add(task); // Add each task to tasks list
            listModel.addElement(task);
        }// Read tasks from file and add them to the list model

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(listScrollPane, BorderLayout.CENTER);
        

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(setTaskButton);
        buttonPanel.add(deleteTaskButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setTaskButton || e.getSource() == deleteTaskButton) {
            String inputCode = (String) JOptionPane.showInputDialog(this, "Enter parentCode:", "Input", JOptionPane.PLAIN_MESSAGE, null, null, "");
            System.out.println("Parent Code: " + this.parentCode);  
            System.out.println("Input Code: " + inputCode);

            if (inputCode != null && inputCode.equals(parentCode)) {
                if (e.getSource() == setTaskButton) {
                    TaskDialog dialog = new TaskDialog(this);
                    dialog.setVisible(true);
                    Task newTask = dialog.getTask();
                    if (newTask != null) {
                        tasks.add(newTask);
                        listModel.addElement(newTask);
                        TaskWriter.writeTasksToFile(tasks, idcode);
                    }
                } else if (e.getSource() == deleteTaskButton) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        System.out.println("Tasks before deletion: " + tasks);
                        tasks.remove(selectedIndex);
                        listModel.remove(selectedIndex);
                        System.out.println(tasks); // Add this line
                        TaskWriter.writeTasksToFile(tasks, idcode);
                    } else {
                        JOptionPane.showMessageDialog(this, "Please select a task to delete.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong Code");
            }
        }
    }

    // public static void main(String[] args) {
    //     String idcode = "1";
    //     String parentCode = "1";
    //     System.out.println("Parent Code: " + parentCode); // Add this line
    //     new TaskListGUI(idcode, parentCode);
    // }

}

class TaskListRenderer extends JLabel implements ListCellRenderer<Task> {
    public TaskListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected, boolean cellHasFocus) {
        setText(task.getFullDescription());
        setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); // 设置任务字体大小为20
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
