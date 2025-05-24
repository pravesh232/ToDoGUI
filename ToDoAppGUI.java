import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ToDoAppGUI {
    private JFrame frame;
    private DefaultListModel<Task> taskModel;
    private JList<Task> taskList;
    private JTextField taskField;
    private ArrayList<Task> tasks;

    public ToDoAppGUI() {
        tasks = new ArrayList<>();
        frame = new JFrame("📝 My To-Do List");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Main List Panel
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taskList.setFixedCellHeight(30);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Tasks"));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input Field
        taskField = new JTextField();
        taskField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JButton addButton = new JButton("➕ Add");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> addTask());

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Bottom Buttons
        JButton markDoneButton = new JButton("✔ Mark Done");
        JButton deleteButton = new JButton("🗑 Delete");

        markDoneButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        markDoneButton.setBackground(new Color(52, 152, 219));
        markDoneButton.setForeground(Color.WHITE);

        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);

        markDoneButton.addActionListener(e -> markSelectedTaskDone());
        deleteButton.addActionListener(e -> deleteSelectedTask());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(markDoneButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    private void addTask() {
        String desc = taskField.getText().trim();
        if (!desc.isEmpty()) {
            Task task = new Task(desc);
            tasks.add(task);
            taskModel.addElement(task);
            taskField.setText("");
        }
    }

    private void markSelectedTaskDone() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            Task task = taskModel.getElementAt(index);
            task.markDone();
            taskList.repaint();
        }
    }

    private void deleteSelectedTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            tasks.remove(index);
            taskModel.remove(index);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoAppGUI::new);
    }
}
