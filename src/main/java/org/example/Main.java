package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.setProperty("org.apache.pdfbox.disableFontScanning", "true"); // avoid loading system fonts, cuz pdf part error
        try {
            // 创建并启动窗口
            // create and launch
            EditorWindow editorWindow = new EditorWindow();
            editorWindow.launch();
        } catch (Exception e) {
            // 捕获记录异常
            // catch and log any exceptions
            e.printStackTrace();
            System.err.println("Error occurred while launching the application: " + e.getMessage());
        }


     /*
        EditorWindow editorWindow = new EditorWindow();
        editorWindow.launch();
      */
        }
}