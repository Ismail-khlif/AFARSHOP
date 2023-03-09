package pidev.afarshop.Service.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordencoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

   /* public User addUser(User user)throws IOException {
        user.setImages(image.getBytes());
        return userRepository.save(user);

    }*/
   public void createUser(User user, MultipartFile image) throws IOException {
       user.setImages(image.getBytes());
       user.setPassword(passwordencoder.encode(user.getPassword()));
       userRepository.save(user);
   }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

   /* public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/

    //mdpoublier
    /*
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User customer = userRepository.findByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            userRepository.save(customer);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}*/
}
