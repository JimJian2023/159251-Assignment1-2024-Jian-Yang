package org.example;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import java.io.File;
public class SyntaxHighlighter {
    public void applySyntaxHighlighting(RSyntaxTextArea textArea, File file) {
        String fileName = file.getName();

        // 根据文件扩展名设置不同的语法样式
        // Set the syntax editing style for Java
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

        // Apply a theme if available (optional)
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/src/main/rulesets/theme.xml"));
            theme.apply(textArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}