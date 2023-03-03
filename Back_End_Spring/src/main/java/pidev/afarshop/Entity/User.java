package pidev.afarshop.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long UserId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String address;
    @Temporal (TemporalType.DATE)
    private Date dayOfBirth;
    private String cin;
    private String telNum;
    private String Etat;
    /*
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

     */
    @Enumerated(EnumType.STRING)
    private Role roles;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;




    /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }*/
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return Arrays.asList(new SimpleGrantedAuthority(roles.name()));
   }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

        public String getfirstname(){
            return firstname;
        }
        public void setfirstname(String firstname){
            this.firstname=firstname;

        }
        public String getlastname(){
            return lastname;
        }
        public void setlastname (String lastname){
            this.lastname = lastname;
        }

        public String getemail () {
            return email;
        }

        public void setemail (String email){
            this.email = email;
        }


}