package org.example;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

    public static void updateDateTime(final JLabel dateTimeLabel) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        final String dateTimeString = formatter.format(new Date());

    }

    public static void insertTimeAndDate(final JTextArea textArea) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        final String dateTime = formatter.format(new Date());
        textArea.insert(dateTime + "\n", 0);
    }
}