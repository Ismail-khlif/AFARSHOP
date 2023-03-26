package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class NotificationDto {

    Long notificationId;

    String message;

    Date createdAt;

    boolean isRead;

    UserDto user;
}
