import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private double firstOperand = 0;
    private String operator = null;
    private boolean startNewNumber = true;

    public BasicCalculator() {
        setTitle("Basic Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Monospaced", Font.BOLD, 24));
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(300, 400));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            panel.add(button);

            if (label.matches("[0-9\\.]")) {
                button.setBackground(new Color(240, 240, 240));
            } else if (label.matches("[+\\-*/]")) {
                button.setBackground(new Color(255, 165, 0));
                button.setForeground(Color.WHITE);
            } else if (label.equals("=")) {
                button.setBackground(new Color(60, 179, 113));
                button.setForeground(Color.WHITE);
            }
        }
        
        JButton clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.setBackground(new Color(220, 20, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> clearCalculator());
        
        panel.add(clearButton, 0); 

        return panel;
    }

    private void clearCalculator() {
        displayField.setText("0");
        firstOperand = 0;
        operator = null;
        startNewNumber = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9\\.]")) {
            handleDigit(command);
        } else if (command.matches("[+\\-*/]")) {
            handleOperator(command);
        } else if (command.equals("=")) {
            calculateResult();
        }
    }

    private void handleDigit(String digit) {
        if (startNewNumber) {
            if (digit.equals(".")) {
                displayField.setText("0.");
            } else {
                displayField.setText(digit);
            }
            startNewNumber = false;
        } else {
            if (digit.equals(".")) {
                if (!displayField.getText().contains(".")) {
                    displayField.setText(displayField.getText() + ".");
                }
            } else {
                displayField.setText(displayField.getText() + digit);
            }
        }
    }

    private void handleOperator(String newOperator) {
        if (operator != null && !startNewNumber) {
            calculateResult();
        }

        try {
            firstOperand = Double.parseDouble(displayField.getText());
            operator = newOperator;
            startNewNumber = true;
        } catch (NumberFormatException ex) {
            displayField.setText("Error");
            clearCalculator();
        }
    }

    private void calculateResult() {
        if (operator == null || startNewNumber) {
            return;
        }

        try {
            double secondOperand = Double.parseDouble(displayField.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand == 0) {
                        displayField.setText("Error: Div by Zero");
                        clearCalculator();
                        return;
                    }
                    result = firstOperand / secondOperand;
                    break;
            }

            displayField.setText(String.valueOf(result));
            firstOperand = result;
            operator = null;
            startNewNumber = true;

        } catch (NumberFormatException ex) {
            displayField.setText("Error");
            clearCalculator();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BasicCalculator();
        });
    }
}