package org.example;

import javax.swing.*;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AboutDialog(final JFrame parent) {
        super(parent, "About", true);


        final JLabel label = new JLabel("<html><body style='text-align: center;'>Text Editor<br>Developed by Jim Jian and Pengfei Yang</body></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);


        initializeDialog(parent, label);
    }


    private void initializeDialog(final JFrame parent, final JLabel label) {
        getContentPane().add(label);
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
}