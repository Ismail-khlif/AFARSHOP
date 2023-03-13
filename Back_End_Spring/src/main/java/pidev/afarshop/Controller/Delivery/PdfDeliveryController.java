package pidev.afarshop.Controller.Delivery;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lowagie.text.DocumentException;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Repository.Order1Repository;
import pidev.afarshop.Service.Delivery.PdfGeneratorDelivery;


@Controller

public class PdfDeliveryController {

    @Autowired
    PdfGeneratorDelivery pdfGeneratorDelivery;
    @Autowired
    Order1Repository order1Repository;


    @GetMapping("/pdf/generateDelivery/{order-id}")
    public void generatePDFDelivery(HttpServletResponse response,@PathVariable("order-id") Long orderId) throws IOException, DocumentException
    { response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attatchement; filename=Report.." + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);


        PdfGeneratorDelivery exporter = new PdfGeneratorDelivery();
        Order1 order1 = order1Repository.findById(orderId).orElse(null);
        exporter.generatePdfDelivery(response, order1);
    }

}




