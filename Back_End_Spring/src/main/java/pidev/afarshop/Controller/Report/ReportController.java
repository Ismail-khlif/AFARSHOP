package pidev.afarshop.Controller.Report;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Service.Mail.MailService;
import pidev.afarshop.utils.ExcelUserListReportView;
import pidev.afarshop.utils.PdfUserListReportView;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RequestMapping("/Report")
@Controller
public class ReportController {


     @Autowired
     UserRepository user;
     MailService emailService;
    private static final String QR_CODE_IMAGE_PATH = "./src/main/QRCode";


//excel
    @GetMapping(value = "/Rapport/excel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list = user.findAll();

        return new ModelAndView(new ExcelUserListReportView(), "usersList", list);
    }
    //pdf
    @GetMapping(value = "/Rapport/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<User> list = user.findAll();

        return new ModelAndView(new PdfUserListReportView(), "usersList", list);
    }


}
