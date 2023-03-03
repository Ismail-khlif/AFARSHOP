package pidev.afarshop.Controller.Report;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Service.Mail.MailService;
import pidev.afarshop.utils.ExcelUserListReportView;
import pidev.afarshop.utils.PdfUserListReportView;
import pidev.afarshop.utils.QrCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ReportController {/*

    @Autowired
    private User user;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private MailService emailService;

    @RequestMapping(value = "/Rapport/excel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list = user.findAll();

        return new ModelAndView(new ExcelUserListReportView(), "usersList", list);
    }

    @RequestMapping(value = "/Rapport/qrCode")
    public ModelAndView qrCode(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list;
        list = user.findAll();
        String data = "";
        for (User user : list) {
            data = data + user.getfirstname() + " " + user.getlastname() + " " + user.getemail() + "\n";
        }
        try {
            qrCodeService.generateQrCode(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ModelAndView();
    }
   /* @RequestMapping(value = "/Rapport/email")
    public ModelAndView email(HttpServletRequest request, HttpServletResponse response) {

        emailService.sendMail("ngorsecka@gmail.com",
                "Spring boot", "Bonjour, nous testons spirng mailer");

        return new ModelAndView();
    }*/
   /* @RequestMapping(value = "/Rapport/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<User> list = user.findAll();

        return new ModelAndView(new PdfUserListReportView(), "usersList", list);
    }*/
}
