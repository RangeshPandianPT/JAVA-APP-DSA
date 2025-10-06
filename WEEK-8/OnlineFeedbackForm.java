import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineFeedbackForm extends JFrame implements ActionListener {

    private ButtonGroup ratingGroup;
    private JTextArea commentsArea;
    private JButton submitButton;
    private JLabel thankYouLabel;

    public OnlineFeedbackForm() {
        setTitle("Online Feedback Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        thankYouLabel = new JLabel("", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Serif", Font.BOLD, 16));
        thankYouLabel.setForeground(new Color(0, 100, 0));
        add(thankYouLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel ratingPrompt = new JLabel("Rate our service (1-5 Stars):");
        ratingPrompt.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(ratingPrompt);

        panel.add(Box.createVerticalStrut(10));

        JPanel radioPanel = createRatingPanel();
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(radioPanel);

        panel.add(Box.createVerticalStrut(15));

        JLabel commentsPrompt = new JLabel("Comments:");
        commentsPrompt.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(commentsPrompt);

        commentsArea = new JTextArea(5, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentsArea);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(Box.createVerticalStrut(5));
        panel.add(scrollPane);

        panel.add(Box.createVerticalStrut(20));

        submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(this);
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(submitButton);

        return panel;
    }

    private JPanel createRatingPanel() {
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingGroup = new ButtonGroup();

        for (int i = 1; i <= 5; i++) {
            JRadioButton radioButton = new JRadioButton(String.valueOf(i));
            radioButton.setActionCommand(String.valueOf(i));
            ratingGroup.add(radioButton);
            radioPanel.add(radioButton);
        }
        return radioPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String selectedRating = null;
            if (ratingGroup.getSelection() != null) {
                selectedRating = ratingGroup.getSelection().getActionCommand();
            }
            String comments = commentsArea.getText().trim();

            if (selectedRating == null) {
                JOptionPane.showMessageDialog(this, "Please select a star rating.", "Missing Rating", JOptionPane.WARNING_MESSAGE);
                return;
            }

            submitButton.setEnabled(false);
            
            String thankYouMessage = "Thank you for your feedback! You rated us " + selectedRating + " stars.";
            
            if (!comments.isEmpty()) {
                thankYouMessage += " Your comment has been recorded.";
            }

            thankYouLabel.setText(thankYouMessage);
            
            commentsArea.setText("");
            ratingGroup.clearSelection();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OnlineFeedbackForm();
        });
    }
}