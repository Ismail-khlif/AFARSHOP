package pidev.afarshop.Auth;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pidev.afarshop.Config.JwtService;
import pidev.afarshop.Repository.TokenRepository;
import pidev.afarshop.Repository.UserRepository;
import pidev.afarshop.Entity.Token;
import pidev.afarshop.Entity.TokenType;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Service.Mail.MailService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private MailService mailService;

   public AuthenticationResponse register(RegisterRequest request) {
       var user = User.builder()
               .username(request.getFirstname())
               .lastname(request.getLastname())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .roles(request.getRole())
               .build();
       var savedUser = repository.save(user);
       var jwtToken = jwtService.generateToken(user);
       saveUserToken(savedUser, jwtToken);
       return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
   }



    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return "{\"token\": \""+jwtToken+"\",\"user\": \""+user.getUsername()+"\"}";

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public AuthenticationResponse demResetPassword(String email) throws MessagingException {
        User user = repository.findByEmail(email).get();
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;
        user.setCodeReset(randomNumber);
        mailService.sendWelcomeEmail(user.getemail(),"reset","reset");
        return AuthenticationResponse.builder()
                .user(user)
                .build();
    }
    public String criptMDP(String  pwd){
        return  passwordEncoder.encode(pwd);
    }
}
