package org.example;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

public class SyntaxHighlighter {
    public void applySyntaxHighlighting(RSyntaxTextArea textArea) {
        // Set the syntax editing style for Java
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);

        // Apply a theme if available (optional)
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/path/to/your/theme.xml"));
            theme.apply(textArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}