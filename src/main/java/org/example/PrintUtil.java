package org.example;
/*
    Print
 */
import javax.swing.*;
import java.awt.print.PrinterException;

public class PrintUtil {


    private PrintUtil() {

    }

    public static void printText(final JTextArea textArea) {
        try {
            boolean complete = textArea.print();
            if (complete) {
                JOptionPane.showMessageDialog(null, "Printing Complete", "Printer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Printing Canceled", "Printer", JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error printing the text: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}