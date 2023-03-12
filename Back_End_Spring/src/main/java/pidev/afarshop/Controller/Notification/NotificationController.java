package pidev.afarshop.Controller.Notification;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Entity.NotificationModel;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Service.Notification.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @PostMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long UserId, @RequestBody User user) {
        // Update user in the database
        // ...

        // Send notification to user
        NotificationModel notification = new NotificationModel("User updated", "Your profile has been updated");
       // NotificationService.sendNotification(user.getUserId(), notification);

        return ResponseEntity.ok().build();
    }


}
