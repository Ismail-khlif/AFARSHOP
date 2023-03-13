package pidev.afarshop.Service.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Notification;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.NotificationRepository;
import pidev.afarshop.Repository.UserRepository;

import java.util.List;
@Service
public class Notification1Service implements INotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Notification> findNotificationsByUser(Long userId) {
        return notificationRepository.userNotification(userId);
    }

    @Override
    public Notification addNotification(Notification notification, String username) {
        User user = userRepository.findByUsername(username).get();
        notification.setRead(false);
        notification.setUser(user);
        return notificationRepository.save(notification);
    }
    @Override
    public void deleteNotification(Long notificationId) {
        Notification notif = notificationRepository.findById(notificationId).orElse(null);
        notificationRepository.delete(notif);

    }
    @Override
    public List<Notification> findAllNotifications() {
        // TODO Auto-generated method stub
        return notificationRepository.findAll();
    }

    @Override
    public void markNotifAsRead(Long  idNotif) {
        Notification notification = notificationRepository.findById(idNotif).orElse(null);
        notification.setRead(true);
        notificationRepository.save(notification);

    }

    @Override
    public void markNotifAsUnRead(Long idNotif) {
        Notification notification = notificationRepository.findById(idNotif).orElse(null);
        notification.setRead(false);
        notificationRepository.save(notification);

    }


}


