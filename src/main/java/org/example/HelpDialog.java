package org.example;

import javax.swing.*;

public class HelpDialog extends JDialog {
    // 添加 serialVersionUID 字段
    private static final long serialVersionUID = 1L;

    public HelpDialog(final JFrame parent) {
        super(parent, "Help", true);
        final JTextArea textArea = new JTextArea();
        textArea.setText("How to Use the Text Editor:\n\n" +
                "1. File Menu:\n" +
                "   - New Window: Create a new text editor window.\n" +
                "   - Open: Open an existing text file or OpenDocument file.\n" +
                "   - Save: Save the current text as a .txt file.\n" +
                "   - Print: Print the current document.\n" +
                "   - Exit: Close the current text editor.\n\n" +
                "2. Edit Menu:\n" +
                "   - Select All: Select all text in the editor.\n" +
                "   - Copy: Copy selected text to the clipboard.\n" +
                "   - Paste: Paste text from the clipboard.\n" +
                "   - Cut: Cut selected text and copy it to the clipboard.\n" +
                "   - Insert Time/Date: Insert the current date and time at the top.\n\n" +
                "3. Help Menu:\n" +
                "   - About: Information about the text editor.\n" +
                "   - Help: Instructions on how to use the text editor.");
        textArea.setEditable(false);
        getContentPane().add(new JScrollPane(textArea));
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}
