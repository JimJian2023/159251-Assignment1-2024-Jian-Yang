package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplaceDialog extends JDialog {
    private JTextField searchField;
    private JTextField replaceField;
    private JButton replaceButton;
    private JTextArea textArea;

    public ReplaceDialog(Frame owner) {
        super(owner, "Replace Text", true);
        textArea = ((EditorWindow) owner).getTextArea();
        initializeComponents();
    }

    private void initializeComponents() {
        searchField = new JTextField(20);
        replaceField = new JTextField(20);
        replaceButton = new JButton("Replace");

        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replaceText();
            }
        });

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