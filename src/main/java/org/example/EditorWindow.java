package org.example;

import javax.swing.*;

public class EditorWindow extends JFrame{
    private JTextArea textArea;

    public EditorWindow(){
        initializeUI();
    }
    private void initializeUI(){
        textArea = new JTextArea();
        textArea.setTabSize(4);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("Text Editor");

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);

        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Call the setupMenu() method
        setupMenu();
    }

    public void launch(){
        setVisible(true);
    }

    private void setupMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        openMenuItem.addActionListener(e -> FileOperation.openFile(this, textArea));
        saveMenuItem.addActionListener(e -> FileOperation.saveFile(this, textArea));

        exitMenuItem.addActionListener(e -> System.exit(0));


        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }


}


