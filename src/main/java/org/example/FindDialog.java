package org.example;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindDialog extends JDialog {
    private JTextField searchField;
    private JButton findButton;
    private JTextArea textArea;

    public FindDialog(Frame owner) {
        super(owner, "Find Text", true);
        textArea = ((EditorWindow) owner).getTextArea();
        initializeComponents();
    }

    private void initializeComponents() {
        searchField = new JTextField(20);
        findButton = new JButton("Find");

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findText();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(findButton, BorderLayout.EAST);

        getContentPane().add(panel, BorderLayout.NORTH);
        setSize(300, 100);
        setLocationRelativeTo(getOwner());
    }

    private void findText() {
        String searchText = searchField.getText();
        String textContent = textArea.getText();

        // 清除之前的高亮
        Highlighter highlighter = textArea.getHighlighter();
        highlighter.removeAllHighlights();

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Find", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int index = textContent.indexOf(searchText);
        if (index >= 0) {
            try {
                while (index >= 0) {
                    int end = index + searchText.length();
                    // 高亮显示找到的文本
                    highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                    index = textContent.indexOf(searchText, end);
                }
                textArea.requestFocusInWindow();
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Text not found.", "Find", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
