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


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
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

}
