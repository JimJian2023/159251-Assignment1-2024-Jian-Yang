package org.example;

import javax.swing.*;
import java.io.*;

public class FileOperation {
    public static void openFile(EditorWindow editorWindow, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(editorWindow);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(EditorWindow editorWindow, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(editorWindow);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
