package pidev.afarshop.Entity;

import lombok.Data;

@Data
public class NotificationModel {
    private Long id;
        private String title;
        private String message;

        public NotificationModel(String title, String message) {
            this.title = title;
            this.message = message;
        }


}


