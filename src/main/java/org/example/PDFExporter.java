package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PDFExporter {

    public static void exportToPDF(JFrame parent, JTextArea textArea, File file) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 750); // start position of the text

            String text = textArea.getText();
            float textHeight = 0;
            String[] lines = text.split("\n");

            for (String line : lines) {
                if (textHeight + 15 > 700) { // if text height exceeds the page limit
                    contentStream.endText();
                    contentStream.close();

                    // Add a new page and content stream
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(25, 750);
                    textHeight = 0;
                }

                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -15); // Move down for the next line
                textHeight += 15;
            }

            contentStream.endText();
            contentStream.close();

            document.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
