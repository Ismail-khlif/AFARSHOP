package pidev.afarshop.Controller.User;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pidev.afarshop.Entity.ConfirmationToken;
import pidev.afarshop.Entity.Role;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.ConfirmationTokenRepository;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Service.User.UserService;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API/USERS")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    private PasswordEncoder passwordEncoder;


    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping(value ="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User createUser(@RequestParam("image") MultipartFile image, @RequestParam("username") String username,
                       @RequestParam("firstname") String firstname,
                       @RequestParam("lastname") String lastname,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("address") String address,
                       @RequestParam("cin") String cin,
                       @RequestParam("telNum") String telNum,
                       @RequestParam("roles") Role roles,
                       @RequestParam("dayOfBirth")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
                       )throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setfirstname(firstname);
        user.setImages(image.getBytes());
        user.setlastname(lastname);
        user.setemail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setCin(cin);
        user.setTelNum(telNum);
        user.setRoles(roles);
        user.setDayOfBirth(dayOfBirth);
        userService.createUser(user,image);
        /*QRCodeUser.QRCode(User);*/
        return user;
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/FindUser/FindByFirstName/{firstname}")
    List<User> FindByfirstname(@PathVariable("firstname") String firstname) {
        return userService.FindByfirstname(firstname);
    }


}
