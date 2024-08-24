package org.example;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import javax.swing.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;


public class EditorWindow extends JFrame {
    private RSyntaxTextArea textArea;
    private JLabel dateTimeLabel;

    public EditorWindow() {
        initializeUI();
    }

    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

    private void initializeUI() {
        textArea = new RSyntaxTextArea();
        textArea.setTabSize(4);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // 确保没有默认选择的文本
        textArea.setSelectionStart(0);
        textArea.setSelectionEnd(0);
        textArea.setHighlightCurrentLine(false);

        // 设置背景颜色为白色，避免黄色高亮
        textArea.setBackground(Color.WHITE);


        //textArea.setText("");

        JScrollPane scrollPane = new JScrollPane(textArea);

        dateTimeLabel = new JLabel();
        updateDateTime();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(dateTimeLabel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(topPanel, BorderLayout.CENTER);

        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupMenu();

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
    }

    public void launch() {
        setVisible(true);
    }
    
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newWindowMenuItem = new JMenuItem("New Window");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        JMenuItem exportAsMenuItem = new JMenuItem("Export As");
        JMenuItem printMenuItem = new JMenuItem("Print");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        newWindowMenuItem.addActionListener(e -> openNewWindow());
        openMenuItem.addActionListener(e -> openFile());
        saveMenuItem.addActionListener(e -> saveFile());
        saveAsMenuItem.addActionListener(e -> saveFileAs());
        exportAsMenuItem.addActionListener(e -> exportAsPDF());
        printMenuItem.addActionListener(e -> printText());
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newWindowMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(exportAsMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }
    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem selectAllMenuItem = new JMenuItem("Select All");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem insertTimeMenuItem = new JMenuItem("Insert Time/Date");

        selectAllMenuItem.addActionListener(e -> textArea.selectAll());
        copyMenuItem.addActionListener(e -> textArea.copy());
        pasteMenuItem.addActionListener(e -> textArea.paste());
        cutMenuItem.addActionListener(e -> textArea.cut());
        insertTimeMenuItem.addActionListener(e -> insertTimeAndDate());

        editMenu.add(selectAllMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(insertTimeMenuItem);

        return editMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        JMenuItem helpMenuItem = new JMenuItem("Help");

        aboutMenuItem.addActionListener(e -> showAbout());
        helpMenuItem.addActionListener(e -> showHelp());

        helpMenu.add(aboutMenuItem);
        helpMenu.add(helpMenuItem);

        return helpMenu;
    }

    private JMenu creatSearchMenu() {
        JMenu searchMenu = new JMenu("Search");
        JMenuItem findMenuItem = new JMenuItem("Find");
        JMenuItem replaceMenuItem = new JMenuItem("Replace");

        findMenuItem.addActionListener(e -> findText());
        replaceMenuItem.addActionListener(e -> replaceText());

        searchMenu.add(findMenuItem);
        searchMenu.add(replaceMenuItem);

        return searchMenu;
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(creatSearchMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createHelpMenu());
        setJMenuBar(menuBar);
    }


    private void updateDateTime() {
        DateTimeUtil.updateDateTime(dateTimeLabel);
    }

    private void showAbout() {
        AboutDialog aboutDialog = new AboutDialog(this);
        aboutDialog.setVisible(true);
    }

    private void showHelp() {
        HelpDialog helpDialog = new HelpDialog(this);
        helpDialog.setVisible(true);
    }

    private void openNewWindow() {
        SwingUtilities.invokeLater(() -> new EditorWindow().setVisible(true));
    }

    private void openFile() {
        FileOperation.openFile(this, textArea);
    }

    private void saveFile() {
        FileOperation.saveFile(this, textArea);
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
                setTitle("Text Editor - " + file.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportAsPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export As PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // ensure as .pdf
            if (!file.getName().endsWith(".pdf")) {
                file = new File(file.getAbsolutePath() + ".pdf");
            }
            // method to export as PDF
            PDFExporter.exportToPDF(this, textArea, file);
        }
    }


    private void printText() {
        try {
            textArea.print();
        } catch (java.awt.print.PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error printing the text.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertTimeAndDate() {
        DateTimeUtil.insertTimeAndDate(textArea);
    }

    private void findText() {
        FindDialog findDialog = new FindDialog(this);
        findDialog.setVisible(true);
    }

    private void replaceText() {
        ReplaceDialog replaceDialog = new ReplaceDialog(this);
        replaceDialog.setVisible(true);
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorWindow().setVisible(true));
    }


}