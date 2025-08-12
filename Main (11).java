import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;

    public ToDoApp() {
        // Window settings
        setTitle("To-Do List App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center screen

        // Create components
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");

        // Panel for input & buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(taskField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(ToDoApp.this, "Please enter a task.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(ToDoApp.this, "Please select a task to delete.");
                }
            }
        });

        // Add to window
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoApp().setVisible(true);
        });
    }
}