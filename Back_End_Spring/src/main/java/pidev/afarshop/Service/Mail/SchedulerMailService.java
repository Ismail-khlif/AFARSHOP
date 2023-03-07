package pidev.afarshop.Service.Mail;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Product;
import pidev.afarshop.Entity.ProductCategory;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Service.Product.ProductServices;
import pidev.afarshop.Service.User.UserService;

import javax.mail.MessagingException;
import java.util.List;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class SchedulerMailService {
    UserService userService;
    ProductServices productServices;
    MailService emailService;

    @Scheduled(cron = "0 30 9 * * ?")
    public void runScheduledDailyOffre() throws MessagingException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            ProductCategory cat = productServices.TopProductCategoryByUserThisWeek(user);
            List<Product> p = productServices.findTop4ProductsByCategoryOrderByRecentlyAdded(cat);
            emailService.sendDailyOfferEmail(user, p);
        }
    }

    @Scheduled(cron = "10 * * * * ?")
    public void runScheduledDailyOffre2() throws MessagingException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            ProductCategory cat = productServices.TopProductCategoryByUserThisWeek(user);
            List<Product> p = productServices.findTop4ProductsByCategoryOrderByRecentlyAdded(cat);
            emailService.sendDailyOfferEmail(user, p);
        }
    }
}
