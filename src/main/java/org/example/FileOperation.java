package org.example;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOperation {


    private FileOperation() {

    }

    public static void openFile(final JFrame parent, final RSyntaxTextArea textArea) {
        final JFileChooser fileChooser = new JFileChooser();
        final int option = fileChooser.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();
            final Path filePath = file.toPath();
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                textArea.read(reader, null);
                parent.setTitle("Text Editor - " + file.getName());

                // Apply syntax highlighting
                final SyntaxHighlighter highlighter = new SyntaxHighlighter();
                highlighter.applySyntaxHighlighting(textArea, file);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(final JFrame parent, final RSyntaxTextArea textArea) {
        final JFileChooser fileChooser = new JFileChooser();
        final int option = fileChooser.showSaveDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();
            final Path filePath = file.toPath();
            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                textArea.write(writer);
                parent.setTitle("Text Editor - " + file.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

