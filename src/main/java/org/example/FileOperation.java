package org.example;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import javax.swing.*;
import java.io.*;

public class FileOperation {

    public static void openFile(JFrame parent, RSyntaxTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
                parent.setTitle("Text Editor - " + file.getName());

                // 调用 SyntaxHighlighter 来应用语法高亮
                SyntaxHighlighter highlighter = new SyntaxHighlighter();
                highlighter.applySyntaxHighlighting(textArea, file);


            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(JFrame parent, RSyntaxTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
                parent.setTitle("Text Editor - " + file.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
