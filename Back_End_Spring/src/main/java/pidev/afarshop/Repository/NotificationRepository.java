package pidev.afarshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Notification;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

    public List<Notification> findByUserUsername(String username);

    @Query("select n from Notification n where n.user.UserId=:userId ORDER BY n.createdAt DESC")
    List<Notification> userNotification(@Param("userId") Long userId);

}
