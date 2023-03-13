package pidev.afarshop.Service.Notification;

import pidev.afarshop.Entity.Notification;

import java.util.List;

public interface INotificationService {
    public List<Notification> findNotificationsByUser(Long userId);
    public Notification addNotification(Notification notification, String username);
    public void deleteNotification(Long notificationId);
    public List<Notification> findAllNotifications();
    public void markNotifAsRead(Long  idNotif);
    public void markNotifAsUnRead(Long idNotif);
}
