package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ReplaceDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private final JTextArea textArea;
    private final JTextField searchField;
    private final JTextField replaceField;

    public ReplaceDialog(final Frame owner) {
        super(owner, "Replace Text", true);
        this.textArea = ((EditorWindow) owner).getTextArea();
        this.searchField = new JTextField(20);
        this.replaceField = new JTextField(20);
        initializeComponents();
    }

    private void initializeComponents() {
        JButton replaceButton = new JButton("Replace");

        replaceButton.addActionListener((ActionEvent e) -> replaceText());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Find:"));
        panel.add(searchField);
        panel.add(new JLabel("Replace with:"));
        panel.add(replaceField);
        panel.add(new JLabel());
        panel.add(replaceButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        setSize(400, 150);
        setLocationRelativeTo(getOwner());
    }

    private void replaceText() {
        String searchText = searchField.getText();
        String replaceText = replaceField.getText();
        String textContent = textArea.getText();

        if (textContent.contains(searchText)) {
            textContent = textContent.replace(searchText, replaceText);
            textArea.setText(textContent);
            JOptionPane.showMessageDialog(this, "Text replaced.", "Replace", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Text not found.", "Replace", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
