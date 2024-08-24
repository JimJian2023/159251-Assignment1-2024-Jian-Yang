package org.example;
/*
    处理打印操作
    Print
 */
import javax.swing.*;
import java.awt.print.PrinterException;

public class PrintUtil {

    public static void printText(JTextArea textArea) {
        try {
            textArea.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error printing the text.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
