package pidev.afarshop.Controller.Report;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Service.Mail.MailService;
import pidev.afarshop.utils.ExcelUserListReportView;
import pidev.afarshop.utils.PdfUserListReportView;
import pidev.afarshop.utils.QrCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RequestMapping("/Report")
@Controller
public class ReportController {


     @Autowired
     UserRepository user;
     @Autowired
     QrCodeService qrCodeService;
     MailService emailService;

    @GetMapping(value = "/Rapport/excel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list = user.findAll();

        return new ModelAndView(new ExcelUserListReportView(), "usersList", list);
    }

    @GetMapping(value = "/Rapport/qrCode")
    public String qrCode(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list;
        list = user.findAll();
        String data = "";
        for (User user : list) {
            data = data + user.getfirstname() + " " + user.getlastname() + " " + user.getemail() + "\n";
        }
        String path = null;
        try {
            path =  qrCodeService.generateQrCode(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
return path;

    }

    @RequestMapping(value = "/Rapport/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<User> list = user.findAll();

        return new ModelAndView(new PdfUserListReportView(), "usersList", list);
    }
}
