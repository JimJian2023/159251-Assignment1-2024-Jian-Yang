package org.example;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindDialog extends JDialog {

    private static final long serialVersionUID = 1L;


    private final JTextField searchField;
    private final JTextArea textArea;


    private static final Logger logger = Logger.getLogger(FindDialog.class.getName());

    public FindDialog(Frame owner) {
        super(owner, "Find Text", true);
        textArea = ((EditorWindow) owner).getTextArea();
        searchField = new JTextField(20);
        initializeComponents();
    }

    private void initializeComponents() {

        JButton findButton = new JButton("Find");

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
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

                    highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                    index = textContent.indexOf(searchText, end);
                }
                textArea.requestFocusInWindow();
            } catch (BadLocationException ex) {

                logger.log(Level.SEVERE, "Error highlighting text", ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Text not found.", "Find", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

