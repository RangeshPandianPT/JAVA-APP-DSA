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
    
        JPanel mainContentPanel = new JPanel();

        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        
        laptopCheckBox = new JCheckBox("Laptop ($" + LAPTOP_PRICE + ")");
        phoneCheckBox = new JCheckBox("Phone ($" + PHONE_PRICE + ")");
        headphonesCheckBox = new JCheckBox("Headphones ($" + HEADPHONES_PRICE + ")");
        
        generateBillButton = new JButton("Generate Bill");

        generateBillButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        billTextArea = new JTextArea(15, 30);
        billTextArea.setEditable(false);
        billTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(billTextArea);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(3, 1));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Select Items"));
        itemsPanel.add(laptopCheckBox);
        itemsPanel.add(phoneCheckBox);
        itemsPanel.add(headphonesCheckBox);
        
        mainContentPanel.add(itemsPanel);
        
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        mainContentPanel.add(generateBillButton);
        
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        mainContentPanel.add(scrollPane);

        add(mainContentPanel, BorderLayout.CENTER);

        generateBillButton.addActionListener(this);
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
        SwingUtilities.invokeLater(() -> {
            new ShoppingCartGUI();
        });
    }
}