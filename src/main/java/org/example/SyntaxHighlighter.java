package org.example;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyntaxHighlighter {


    public SyntaxHighlighter() {

    }

    public void applySyntaxHighlighting(final RSyntaxTextArea textArea, final File file) {
        final String fileName = file.getName();


        if (fileName.endsWith(".java")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        } else if (fileName.endsWith(".py")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        } else if (fileName.endsWith(".js")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        } else {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE); // 无法识别的文件类型
        }

        textArea.setCodeFoldingEnabled(true);


        try {
            final Theme theme = Theme.load(getClass().getResourceAsStream("/src/main/rulesets/theme.xml"));
            theme.apply(textArea);
        } catch (IOException e) {
            Logger.getLogger(SyntaxHighlighter.class.getName()).log(Level.SEVERE, "Error applying theme", e);
        }
    }
}
