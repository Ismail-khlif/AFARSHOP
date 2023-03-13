package pidev.afarshop.Service.Delivery;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Repository.BillRepository;
import pidev.afarshop.Repository.Order1Repository;


@Component("pdfGeneratorDelivery")
public class PdfGeneratorDelivery {


    @Autowired
    BillRepository billRepository;
    @Autowired
    Order1Repository order1Repository;

    public void generatePdfDelivery(HttpServletResponse response, Order1 o)throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        try {

            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Rectangle rect= new Rectangle(594,842,0,0);
            rect.setBackgroundColor(Color.decode("#1E1E2E"));
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            //rect.setBorder(2);
            rect.setBorderColor(Color.decode("#F5A503"));
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(11);
            document.add(rect);
            addLogo(document);
            addDocTitle(document);
            getDbData(document, o);
            addSignature(document,o);
            addFooter(document);
            document.close();
            System.out.println("------------------Your PDF Report is ready!-------------------------");

        } catch (FileNotFoundException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void addLogo(Document document) {
        try {
            Image img = Image.getInstance("C:\\Users\\lenovo\\Documents\\Logo AFARSHOP.png");
            img.scalePercent(40, 40);
            img.setAlignment(Element.ALIGN_RIGHT);
            document.add(img);

        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addDocTitle(Document document) throws DocumentException {
        Font fontTiltle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTiltle.setSize(30);
        fontTiltle.setColor(Color.white);
        fontTiltle.setStyle("bold");
        Paragraph p1 = new Paragraph();
        leaveEmptyLine(p1, 2);
        p1.add(new Paragraph("Bill Details", fontTiltle));
        p1.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p1, 2);
        document.add(p1);

    }

    private void getDbData(Document document,Order1 o) throws DocumentException {


       // List<Product> products = billRepository.getProducts(o.getOrderId());
        System.out.println(o.getOrderId());
        //for (Product p: products)
        //{
          //  System.out.println(p.getPrice());
       // }

        Font fontData = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        fontData.setSize(20);
        fontData.setColor(Color.decode("#F5A503"));
        Font fontText = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontText.setSize(20);
        fontText.setColor(Color.white);
        Paragraph p2=new Paragraph();
        p2.add(new Paragraph(o.getDelivery().getFirstName() + "   " +  o.getDelivery().getLastName(), fontData));
        p2.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p2, 1);
        p2.add(new Paragraph("numéro  "  +o.getDelivery().getNumTel().toString(), fontData));
        p2.setAlignment(Element.ALIGN_CENTER);
        p2.setIndentationLeft(11);
        leaveEmptyLine(p2, 3);
        p2.add(new Paragraph("payment amount is  " + String.valueOf(o.getBill().getPaymentAmount()), fontText));
        leaveEmptyLine(p2, 1);
        //for (Product p: products)
        //{
         //   p2.add(new Paragraph( p.getProductName() + "   " + p.getPrice(), fontText));
        //}
        //p2.add(new Paragraph( o., fontText));
        //leaveEmptyLine(p2, 5);
        document.add(p2);


    }

    private void addSignature(Document document,Order1 o) {
        try {
            Font fontSignature = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontSignature.setSize(21);
            fontSignature.setColor(Color.white);
            Paragraph p3=new Paragraph();
            p3.add(new Paragraph("Le "+o.getDelivery().getCreationDate().toLocaleString(), fontSignature));
            leaveEmptyLine(p3, 1);
            p3.setAlignment(Element.ALIGN_RIGHT);
            //leaveEmptyLine(p2, 1);
            p3.setIndentationLeft(30);
            document.add(p3);
            Image img = Image.getInstance("C:\\Users\\lenovo\\Documents\\signaturewhite.png");
            img.scalePercent(10, 10);
            img.setAlignment(Element.ALIGN_LEFT);
            img.setIndentationLeft(21);
            document.add(img);
            Paragraph p2=new Paragraph();
            p2.add(new Paragraph("==========", fontSignature));
            p2.setAlignment(Element.ALIGN_LEFT);
            //leaveEmptyLine(p2, 1);
            p2.add(new Paragraph("Signature", fontSignature));
            p2.setIndentationLeft(30);
            document.add(p2);


        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addFooter(Document document) throws DocumentException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String localDateString = dateFormatter.format(new Date());
        Font fontFooter = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontFooter.setSize(17);
        fontFooter.setColor(32, 103, 66);
        fontFooter.setStyle("bold");
        Paragraph p2 = new Paragraph();
        leaveEmptyLine(p2, 5);
        p2.add(new Paragraph(
                "-----------------Delivery PDF generated on "+ localDateString+ "-----------------" , fontFooter));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
    }

    private static void leaveEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}




