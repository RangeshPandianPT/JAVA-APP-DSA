import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartGUI extends JFrame implements ActionListener {

    private static final double LAPTOP_PRICE = 1200.00;
    private static final double PHONE_PRICE = 650.00;
    private static final double HEADPHONES_PRICE = 150.00;

    private JCheckBox laptopCheckBox;
    private JCheckBox phoneCheckBox;
    private JCheckBox headphonesCheckBox;
    private JButton generateBillButton;
    private JTextArea billTextArea;

    public ShoppingCartGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Shopping Cart Simulation");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Create a main panel that will hold all components vertically
        JPanel mainContentPanel = new JPanel();
        // Use BoxLayout for vertical stacking (Y_AXIS)
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Instantiate components
        laptopCheckBox = new JCheckBox("Laptop ($" + LAPTOP_PRICE + ")");
        phoneCheckBox = new JCheckBox("Phone ($" + PHONE_PRICE + ")");
        headphonesCheckBox = new JCheckBox("Headphones ($" + HEADPHONES_PRICE + ")");

        generateBillButton = new JButton("Generate Bill");
        // Center the button horizontally
        generateBillButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        billTextArea = new JTextArea(15, 30);
        billTextArea.setEditable(false);
        billTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // Wrap the text area in a scroll pane
        JScrollPane scrollPane = new JScrollPane(billTextArea);

        // Panel for checkboxes
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(3, 1));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Select Items"));
        itemsPanel.add(laptopCheckBox);
        itemsPanel.add(phoneCheckBox);
        itemsPanel.add(headphonesCheckBox);

        // --- Assemble the mainContentPanel using BoxLayout ---
        mainContentPanel.add(itemsPanel);

        // Add vertical spacing (a JRigidArea) to separate items and button
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainContentPanel.add(generateBillButton);

        // Add vertical spacing to separate button and output
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainContentPanel.add(scrollPane);

        // Add the main content panel to the frame's center
        add(mainContentPanel, BorderLayout.CENTER);

        // Add ActionListener
        generateBillButton.addActionListener(this);

        // Pack the frame to ensure components are sized correctly
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateBillButton) {
            generateBill();
        }
    }

    private void generateBill() {
        double totalCost = 0.0;
        StringBuilder billText = new StringBuilder("--- Shopping Cart Bill ---\n");

        if (laptopCheckBox.isSelected()) {
            totalCost += LAPTOP_PRICE;
            billText.append(String.format("Laptop: $%.2f\n", LAPTOP_PRICE));
        }

        if (phoneCheckBox.isSelected()) {
            totalCost += PHONE_PRICE;
            billText.append(String.format("Phone: $%.2f\n", PHONE_PRICE));
        }

        if (headphonesCheckBox.isSelected()) {
            totalCost += HEADPHONES_PRICE;
            billText.append(String.format("Headphones: $%.2f\n", HEADPHONES_PRICE));
        }

        billText.append("----------------------------\n");
        billText.append(String.format("Total: $%.2f", totalCost));

        billTextArea.setText(billText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShoppingCartGUI::new);
    }
}