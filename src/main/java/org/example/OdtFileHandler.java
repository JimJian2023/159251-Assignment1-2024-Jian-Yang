package org.example;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

public class OdtFileHandler {


    private OdtFileHandler() {

    }

    public static void openOdtFile(final JFrame parent, final RSyntaxTextArea textArea, final File file) {
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            // 简单地读取文件内容到字符串中（假设 ODT 文件是纯文本）
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }

            textArea.setText(content.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent, "Error opening ODT file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}