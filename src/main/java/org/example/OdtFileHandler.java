package org.example;
/*
    .odt文件的处理功能
    deal with .odt files
 */
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.*;

public class OdtFileHandler {

    public static void openOdtFile(JFrame parent, JTextArea textArea, File file) {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            textArea.setText(extractor.getText());
            parent.setTitle("Text Editor - " + file.getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent, "Error reading the ODT file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
