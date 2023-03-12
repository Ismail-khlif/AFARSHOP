package pidev.afarshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.*;

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
    private String uid;
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
    @Lob
    private byte[] images;
    @Enumerated(EnumType.STRING)
    private Role roles;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order1> orders;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Answer> answers;


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



    //added by Ismail
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Publication> publications ;

    @JsonIgnore
    @OneToMany(mappedBy ="user" )
    private List<Comment> comments  ;

    @JsonIgnore
    @OneToMany(mappedBy ="user" )
    private List<CommentD> commentDs  ;


    @JsonIgnore
    @OneToMany(mappedBy ="user" )
    private List<Reaction> reactions  ;
    //fin ajout

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Rating> ratings;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<ProductComment> productComments;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    Set<Product> products;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<ProductLike> productLikes;

    @JsonIgnore
    @OneToOne
    Cart cart;



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

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public User(String firstname) {
        this.firstname = firstname;
    }
}
 
