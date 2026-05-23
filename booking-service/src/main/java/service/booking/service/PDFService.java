package service.booking.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PDFService {

  public String generatePdf(String bookingId) throws Exception {

    Path receiptDirectoryPath = Paths.get("receipts");

    File receiptDirectory = new File(receiptDirectoryPath.toString());

    if (!receiptDirectory.exists()) {
      receiptDirectory.mkdirs();
    }

    String fileName = bookingId + ".pdf";

    String path =
            receiptDirectoryPath.resolve(fileName).toString();

    Document document = new Document();

    PdfWriter.getInstance(
            document,
            new FileOutputStream(path)
    );

    document.open();

    document.add(new Paragraph("Booking Receipt"));
    document.add(new Paragraph("Booking ID: " + bookingId));

    document.close();

    return path;
  }
}