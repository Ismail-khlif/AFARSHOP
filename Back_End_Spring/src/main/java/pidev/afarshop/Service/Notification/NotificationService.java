package pidev.afarshop.Service.Notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Notification;
import pidev.afarshop.Auth.AuthenticationResponse;
import pidev.afarshop.Config.JwtService;
import pidev.afarshop.Entity.NotificationModel;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import pidev.afarshop.Repository.TokenRepository;



public class NotificationService {
    private static final FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
    /*private final JwtService jwtService;*/
    /*private final TokenRepository tokenRepository;*/

   /* public static void sendNotification(Long UserId, NotificationModel notification) {
        // Get user's device token from database
        String deviceToken = getUserDeviceToken(UserId);

        // Create FCM message
        Message message = Message.builder()
                .setNotification(new Notification("notification", notification , notification.getId()))
                .setToken(deviceToken)
                .build();

        // Send FCM message
        try {
            String response = firebaseMessaging.send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    private static String getUserDeviceToken(Long UserId) {
        // Get device token from database for the user with the given userId

        return deviceToken;
    }*/
}
