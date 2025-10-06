import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementForm extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField departmentField;
    private JTextField salaryField;
    private JButton submitButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeManagementForm() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        JScrollPane tableScrollPane = createTablePanel();
        add(tableScrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        JLabel deptLabel = new JLabel("Department:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(deptLabel, gbc);

        departmentField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(departmentField, gbc);

        JLabel salaryLabel = new JLabel("Salary:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(salaryLabel, gbc);

        salaryField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(salaryField, gbc);

        submitButton = new JButton("Add Employee");
        submitButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(submitButton, gbc);

        return panel;
    }

    private JScrollPane createTablePanel() {
        String[] columnNames = {"Name", "Department", "Salary"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        
        return new JScrollPane(employeeTable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText().trim();
            String department = departmentField.getText().trim();
            String salaryStr = salaryField.getText().trim();

            if (name.isEmpty() || department.isEmpty() || salaryStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double salary = Double.parseDouble(salaryStr);
                
                Object[] rowData = {name, department, String.format("%.2f", salary)};
                tableModel.addRow(rowData);

                nameField.setText("");
                departmentField.setText("");
                salaryField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salary must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeManagementForm();
        });
    }
}