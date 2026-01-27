package com.nesa.springboot_rms.temp;

import java.time.LocalDate;
import java.util.EnumSet;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@RestController
public class ReportController {

    @GetMapping(value = "/report", produces = "application/pdf")
    public ResponseEntity<byte[]> getReport() throws Exception {
        byte[] pdf = ReportService.generatePdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}

class ReportService {

    public static byte[] generatePdf() throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // ===== CENTER HEADING =====
        Paragraph heading = new Paragraph("Store Name\nSub Name Place Mob")
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setFontSize(14);

        document.add(heading);
        document.add(new Paragraph("\n"));

        // ===== CLIENT NAME (LEFT) & DATE (RIGHT) =====
        Table infoTable = new Table(new float[] { 1, 1 });
        infoTable.setWidth(UnitValue.createPercentValue(100));

        infoTable.addCell(new Cell()
                .add(new Paragraph("Client Name: __________"))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT));

        infoTable.addCell(new Cell()
                .add(new Paragraph("Date: " + LocalDate.now()))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT));

        document.add(infoTable);
        document.add(new Paragraph("\n"));

        // ===== MULTI-PAGE TABLE =====
        float[] columnWidths = {
                1, 2, 2, 2, 2, // Left side
                1, 2, 2, 2, 2 // Right side
        };

        Table boxTable = new Table(columnWidths);
        boxTable.setWidth(UnitValue.createPercentValue(100));
        boxTable.setKeepTogether(false); // IMPORTANT: allow page breaks
        String[] headers = {
                "SlNo", "Date", "BAG", "BIN", "Amount",
                "SlNo", "Date", "BAG", "BIN", "Amount"
        };

        int border = 1;
        for (String h : headers) {
            if(border == 1 || border == 6){
                boxTable.addHeaderCell(cell(h, TextAlignment.CENTER, BoldDiv.TEXT, BoldDiv.TOP, BoldDiv.BOTTOM, BoldDiv.LEFT));
            }else if(border == 5 || border == 10){
                boxTable.addHeaderCell(cell(h, TextAlignment.CENTER, BoldDiv.TEXT, BoldDiv.TOP, BoldDiv.BOTTOM, BoldDiv.RIGHT));
            }else{
                boxTable.addHeaderCell(cell(h, TextAlignment.CENTER, BoldDiv.TEXT, BoldDiv.TOP, BoldDiv.BOTTOM));
            }
            // boxTable.addHeaderCell(
            //         new Cell()
            //                 .add(new Paragraph(h).setBold())
            //                 .setTextAlignment(TextAlignment.CENTER));
        }
        int leftSlNo = 1;
        int rightSlNo = 17;

        double totalAmount = 0;

        for (int i = 1; i <= 16; i++) {

            // ===== LEFT SIDE (1–16) =====
            boxTable.addCell(cell(String.valueOf(leftSlNo), TextAlignment.CENTER, BoldDiv.LEFT)); // SlNo
            boxTable.addCell(cell("2026-01-" + String.format("%02d", leftSlNo), TextAlignment.LEFT)); // Date
            boxTable.addCell(cell("B-" + leftSlNo, TextAlignment.CENTER)); // BAG
            boxTable.addCell(cell("C-" + leftSlNo, TextAlignment.CENTER)); // BIN
            boxTable.addCell(cell("100", TextAlignment.RIGHT, BoldDiv.RIGHT)); // Amount

            totalAmount += 100;
            leftSlNo++;

            // ===== RIGHT SIDE (17–31) =====
            if (rightSlNo <= 31) {
                boxTable.addCell(cell(String.valueOf(rightSlNo), TextAlignment.CENTER)); // SlNo
                boxTable.addCell(cell("2026-01-" + String.format("%02d", rightSlNo), TextAlignment.LEFT)); // Date
                boxTable.addCell(cell("C-" + rightSlNo, TextAlignment.CENTER)); // BAG
                boxTable.addCell(cell("B-" + rightSlNo, TextAlignment.CENTER)); // BIN
                boxTable.addCell(cell("100", TextAlignment.RIGHT, BoldDiv.RIGHT)); // Amount

                totalAmount += 100;
                rightSlNo++;
            } else {
                // Empty cells if month has < 31 days
                for (int j = 0; j < 5; j++) {
                    boxTable.addCell("");
                }
            }
        }

        // ===== TOTAL ROW =====

        // Left side empty
        for (int i = 0; i < 5; i++) {
            boxTable.addCell(
                    new Cell()
                            .add(new Paragraph(""))
                            .setBorder(Border.NO_BORDER));
        }

        // Right side total label
        boxTable.addCell(
                new Cell(1, 4)
                        .add(new Paragraph("TOTAL").setBold())
                        .setTextAlignment(TextAlignment.RIGHT));

        // Right side total amount
        boxTable.addCell(
                new Cell()
                        .add(new Paragraph(String.valueOf(totalAmount)).setBold())
                        .setTextAlignment(TextAlignment.RIGHT));

        document.add(boxTable);

        document.add(new Paragraph("\n"));

        document.add(
                new Paragraph("Bag = $20    Bin = $25")
                        .setFontSize(10)
                        .setBold());

        document.add(
                new Paragraph("System generated file. No signature required.")
                        .setFontSize(9)
                        .setItalic());

        document.close();
        return out.toByteArray();
    }

    private static Cell cell(String text, TextAlignment align, BoldDiv... divs) {

        Paragraph paragraph = new Paragraph(text);

        Cell cell = new Cell()
                .add(paragraph)
                .setTextAlignment(align);

        if (divs == null || divs.length == 0) {
            return cell;
        }

        for (BoldDiv div : divs) {
            switch (div) {
                case TOP:
                    cell.setBorderTop(new SolidBorder(2));
                    break;
                case RIGHT:
                    cell.setBorderRight(new SolidBorder(2));
                    break;
                case BOTTOM:
                    cell.setBorderBottom(new SolidBorder(2));
                    break;
                case LEFT:
                    cell.setBorderLeft(new SolidBorder(2));
                    break;
                case TEXT:
                    paragraph.setBold();
                    break;
            }
        }
        return cell;
    }

    private enum BoldDiv {
        RIGHT, LEFT, TOP, BOTTOM, TEXT
    }

}
