package pidev.afarshop.Service.Store;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import pidev.afarshop.Entity.Store;
import pidev.afarshop.Repository.StoreRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component ("StorePdfGenerator")
public class StorePdfGenerator {
    @Autowired
    StoreRepository storeRepository;

    public StorePdfGenerator(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StorePdfGenerator() {

    }

    public void generatePdfReport(HttpServletResponse response, Store s)throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        try {

            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            addLogo(document);
            addDocTitle(document);
            getDbData(document,s);
            addFooter(document);
            document.close();
            System.out.println("------------------Your PDF Report is ready!-------------------------");

        } catch (FileNotFoundException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void addLogo(com.lowagie.text.Document document) {
        try {
            Image img = Image.getInstance("C:\\Users\\lenovo\\Documents\\GitHub\\AFARSHOP\\assets\\imageStore.png");
            img.scalePercent(25, 25);
            img.setAlignment(Element.ALIGN_RIGHT);
            document.add(img);

        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addDocTitle(com.lowagie.text.Document document) throws DocumentException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String localDateString = dateFormatter.format(new Date());
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(21);
        fontTiltle.setColor(32, 103, 66);
        fontTiltle.setStyle("bold");
        Paragraph p1 = new Paragraph();
        p1.add(new Paragraph("Store", fontTiltle));
        p1.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p1, 2);
        p1.add(new Paragraph("Store evaluation generated on " + localDateString, fontTiltle));
        leaveEmptyLine(p1, 3);

        document.add(p1);

    }

    private void getDbData(com.lowagie.text.Document document, Store s) throws DocumentException {


        Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontData.setSize(14);
        Paragraph p2=new Paragraph();
        p2.add(new Paragraph("Store ID: "+"   "+s.getStoreId(), fontData));
        p2.setAlignment(Element.ALIGN_LEFT);
        p2.setIndentationLeft(11);
        leaveEmptyLine(p2, 1);
        p2.add(new Paragraph("Store Name: "+"   "+s.getStoreName(), fontData));
        leaveEmptyLine(p2, 1);
        p2.add(new Paragraph("Evaluation: "+"   "+s.getEvaluation(), fontData));
        leaveEmptyLine(p2, 1);
        p2.add(new Paragraph("Score: "+"   "+s.getScore(), fontData));
        leaveEmptyLine(p2, 1);
        p2.add(new Paragraph("Number of Likes: "+"   "+s.getNbLikes(), fontData));
        leaveEmptyLine(p2, 1);
        p2.add(new Paragraph("Number of Dislikes: "+"   "+s.getNbDislikes(), fontData));
        leaveEmptyLine(p2, 1);

        document.add(p2);
    }

    private void addFooter(com.lowagie.text.Document document) throws DocumentException {
        Font fontFooter = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontFooter.setSize(17);
        fontFooter.setColor(32, 103, 66);
        fontFooter.setStyle("bold");
        Paragraph p2 = new Paragraph();
        leaveEmptyLine(p2, 2);
        p2.add(new Paragraph(
                "------------------------End Of Report------------------------", fontFooter));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
    }

    private static void leaveEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }





}
