package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class EditorWindow extends JFrame {

    private static final long serialVersionUID = 1L;


    private RSyntaxTextArea textArea;
    private JLabel dateTimeLabel;

    public EditorWindow() {
        super();
        initializeUI();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

    private void initializeUI() {
        textArea = new RSyntaxTextArea();
        textArea.setTabSize(4);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);


        textArea.setSelectionStart(0);
        textArea.setSelectionEnd(0);
        textArea.setHighlightCurrentLine(false);


        textArea.setBackground(Color.WHITE);

        final JScrollPane scrollPane = new JScrollPane(textArea);
        dateTimeLabel = new JLabel();
        updateDateTime();

        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(dateTimeLabel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(topPanel, BorderLayout.CENTER);

        setTitle("Text Editor");
        setSize(800, 600);
        setLocationRelativeTo(null);

        setupMenu();

        final Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
    }

    public void launch() {
        setVisible(true);
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem newWindowMenuItem = new JMenuItem("New Window");
        final JMenuItem openMenuItem = new JMenuItem("Open");
        final JMenuItem saveMenuItem = new JMenuItem("Save");
        final JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        final JMenuItem exportAsMenuItem = new JMenuItem("Export As");
        final JMenuItem printMenuItem = new JMenuItem("Print");
        final JMenuItem exitMenuItem = new JMenuItem("Exit");

        newWindowMenuItem.addActionListener(e -> openNewWindow());
        openMenuItem.addActionListener(e -> openFile());
        saveMenuItem.addActionListener(e -> saveFile());
        saveAsMenuItem.addActionListener(e -> saveFileAs());
        exportAsMenuItem.addActionListener(e -> exportAsPDF());
        printMenuItem.addActionListener(e -> printText());

        // 避免使用 System.exit()
        exitMenuItem.addActionListener(e -> dispose());

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
        final JMenu editMenu = new JMenu("Edit");
        final JMenuItem selectAllMenuItem = new JMenuItem("Select All");
        final JMenuItem copyMenuItem = new JMenuItem("Copy");
        final JMenuItem pasteMenuItem = new JMenuItem("Paste");
        final JMenuItem cutMenuItem = new JMenuItem("Cut");
        final JMenuItem insertTimeMenuItem = new JMenuItem("Insert Time/Date");

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
        final JMenu helpMenu = new JMenu("Help");
        final JMenuItem aboutMenuItem = new JMenuItem("About");
        final JMenuItem helpMenuItem = new JMenuItem("Help");

        aboutMenuItem.addActionListener(e -> showAbout());
        helpMenuItem.addActionListener(e -> showHelp());

        helpMenu.add(aboutMenuItem);
        helpMenu.add(helpMenuItem);

        return helpMenu;
    }

    private JMenu creatSearchMenu() {
        final JMenu searchMenu = new JMenu("Search");
        final JMenuItem findMenuItem = new JMenuItem("Find");
        final JMenuItem replaceMenuItem = new JMenuItem("Replace");

        findMenuItem.addActionListener(e -> findText());
        replaceMenuItem.addActionListener(e -> replaceText());

        searchMenu.add(findMenuItem);
        searchMenu.add(replaceMenuItem);

        return searchMenu;
    }

    private void setupMenu() {
        final JMenuBar menuBar = new JMenuBar();
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
        final AboutDialog aboutDialog = new AboutDialog(this);
        aboutDialog.setVisible(true);
    }

    private void showHelp() {
        final HelpDialog helpDialog = new HelpDialog(this);
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
        final JFileChooser fileChooser = new JFileChooser();
        final int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
                setTitle("Text Editor - " + file.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportAsPDF() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export As PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        final int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // 确保文件名以 .pdf 结尾
            if (!file.getName().endsWith(".pdf")) {
                file = new File(file.getAbsolutePath() + ".pdf");
            }
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
        final FindDialog findDialog = new FindDialog(this);
        findDialog.setVisible(true);
    }

    private void replaceText() {
        final ReplaceDialog replaceDialog = new ReplaceDialog(this);
        replaceDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorWindow().setVisible(true));
    }
}
