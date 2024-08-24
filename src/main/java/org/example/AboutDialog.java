package org.example;

import javax.swing.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame parent) {
        super(parent, "About", true);
        JLabel label = new JLabel("<html><body style='text-align: center;'>Text Editor<br>Developed by Jim Jian and Pengfei Yang</body></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(label);
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
}
