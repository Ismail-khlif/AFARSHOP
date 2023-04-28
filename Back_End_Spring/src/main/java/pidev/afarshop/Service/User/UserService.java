package pidev.afarshop.Service.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidev.afarshop.Auth.AuthenticationService;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Service.Mail.MailService;


import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordencoder;
    private MailService emailService;
    private final AuthenticationService authenticationService;


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

   public List<User> FindByfirstname(String firstname) {
        List<User> listUser = new ArrayList<User>();
        listUser = userRepository.FindByfirstname(firstname);
        if (listUser.size() == 0) {
            System.out.println("There Are No name In The DataBase With The Provided name ");
        } else {
            System.out.println("Name Avariable : ");
            System.out.println(listUser);
        }
        return listUser;

    }
    public User demReserPassword(String email) throws MessagingException {
        User user = userRepository.findByEmail(email).get();
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;
        user.setCodeReset(randomNumber);
        //emailService.sendCodeReset(user);
        return userRepository.save(user);
    }

    public String resetPassword(Integer code, String pwd) {
        User user = userRepository.findByCodeReset(code).get();
        if (user == null){
            return "User Not Found";
        }
        else {
            user.setPassword(authenticationService.criptMDP(pwd));
            return "done";
        }
    }

}
