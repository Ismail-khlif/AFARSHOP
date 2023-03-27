package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Notification;
import pidev.afarshop.Entity.User;

import java.util.Date;

@Builder
@Data
public class NotificationDto {

    Long notificationId;

    String message;

    Date createdAt;

    boolean isRead;
    @JsonIgnore
    UserDto user;
    public static Notification toEntity(NotificationDto notificationDto) {
        if (notificationDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        User user1 = UserDto.toEntity(notificationDto.getUser());
        return Notification.builder()
                .notificationId(notificationDto.getNotificationId())
                .message(notificationDto.getMessage())
                .createdAt(notificationDto.getCreatedAt())
                .isRead(notificationDto.isRead())
                .user(user1)
                .build();
    }
    public static NotificationDto toDto(Notification notification){
        if(notification==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        UserDto user1Dto = UserDto.toDto(notification.getUser());
        return  NotificationDto.builder()
                .notificationId(notification.getNotificationId())
                .message(notification.getMessage())
                .createdAt(notification.getCreatedAt())
                .isRead(notification.isRead())
                .user(user1Dto)
                .build();
    }
}
