package pidev.afarshop.Controller.Notification;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.Notification;
import pidev.afarshop.Service.Notification.Notification1Service;
import pidev.afarshop.Service.User.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Notification/")
public class NotificationController {

    Notification1Service notification1Service;

    @GetMapping("/notifications/{userID}")
    public List<Notification> findNotificationsByUser(@PathVariable(name="userID") Long userID) {
        return notification1Service.findNotificationsByUser(userID);
    }

    @PostMapping("/notification/save/{username}")
    public Notification addNotification(@RequestBody Notification notification,@PathVariable(name="username") String username) {
        return notification1Service.addNotification(notification, username);
    }

    @DeleteMapping("/notification/delete/{notificationId}")
    public void deleteNotification(@PathVariable(name="notificationId") Long notificationId) {
        notification1Service.deleteNotification(notificationId);
    }

    @GetMapping("/notifications/all")
    public List<Notification> findAllNotifications() {
        return notification1Service.findAllNotifications();
    }

    @PutMapping("/notification/read")
    public void markNotifAsRead(@RequestBody Long idNotif) {
        notification1Service.markNotifAsRead(idNotif);
    }

    @PutMapping("/notification/unread")
    public void markNotifAsUnRead(@RequestBody Long idNotif) {
        notification1Service.markNotifAsUnRead(idNotif);
    }
}
