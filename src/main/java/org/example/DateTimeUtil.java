package org.example;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static void updateDateTime(JLabel dateTimeLabel) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeString = formatter.format(new Date());
        //dateTimeLabel.setText("Current Time: " + dateTimeString);
    }

    public static void insertTimeAndDate(JTextArea textArea) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(new Date());
        textArea.insert(dateTime + "\n", 0);
    }
}
