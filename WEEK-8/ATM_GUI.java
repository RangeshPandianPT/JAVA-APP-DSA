import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ATM_GUI extends JFrame implements ActionListener {

    private static final String CORRECT_PIN = "1234";
    private double balance = 5000.00;

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel;
    
    private JPasswordField pinField;
    private JLabel statusLabel;
    
    private JLabel balanceLabel;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;

    public ATM_GUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("ATM Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        JPanel loginPanel = createLoginPanel();
        mainPanel.add(loginPanel, "Login");

        JPanel transactionPanel = createTransactionPanel();
        mainPanel.add(transactionPanel, "Transaction");

        setVisible(true);
        cardLayout.show(mainPanel, "Login"); 
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        panel.add(new JLabel("Enter PIN:"), gbc); 

        pinField = new JPasswordField(10); 
        gbc.gridy = 1;
        panel.add(pinField, gbc);

        JButton loginButton = new JButton("Login"); 
        loginButton.addActionListener(this);
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        statusLabel = new JLabel(""); 
        statusLabel.setForeground(Color.RED);
        gbc.gridy = 3;
        panel.add(statusLabel, gbc);

        return panel;
    }

    private JPanel createTransactionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        balanceLabel = new JLabel("Welcome! Please select an option.", SwingConstants.CENTER); 
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(balanceLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        checkBalanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        
        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);    
        withdrawButton.addActionListener(this);   
        
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            processLogin();
        } else if (e.getSource() == checkBalanceButton) {
            checkBalance();
        } else if (e.getSource() == depositButton) {
            deposit();
        } else if (e.getSource() == withdrawButton) {
            withdraw();
        }
    }

    private void processLogin() {
        String enteredPin = new String(pinField.getPassword()); 
        
        if (enteredPin.equals(CORRECT_PIN)) {
            statusLabel.setText("");
            cardLayout.show(mainPanel, "Transaction");
            pinField.setText("");
        } else {
            statusLabel.setText("Incorrect PIN. Try again.");
            pinField.setText("");
        }
    }

    private void checkBalance() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        balanceLabel.setText("Your current balance is: " + formatter.format(balance)); 
    }

    private void deposit() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to deposit:", "Deposit", JOptionPane.PLAIN_MESSAGE);
        
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0) {
                balance += amount;
                checkBalance();
                JOptionPane.showMessageDialog(this, "Deposit of " + NumberFormat.getCurrencyInstance().format(amount) + " successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Deposit amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            if (input != null && !input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void withdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
        
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                checkBalance();
                JOptionPane.showMessageDialog(this, "Withdrawal of " + NumberFormat.getCurrencyInstance().format(amount) + " successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Error: Insufficient Balance.", "Error", JOptionPane.ERROR_MESSAGE); 
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid withdrawal amount.", "Error", JOptionPane.ERROR_MESSAGE); 
                }
            }
        } catch (NumberFormatException ex) {
            if (input != null && !input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ATM_GUI();
        });
    }
}