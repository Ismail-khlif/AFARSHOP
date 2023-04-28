package pidev.afarshop.Auth;

import pidev.afarshop.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;

    //@NotBlank(message = "First name is required")
    private String firstname;

    //@NotBlank(message = "Last name is required")
    private String lastname;

    //@Email(message = "Invalid email address")
    //@NotBlank(message = "Email is required")
    private String email;

    //@NotBlank(message = "Password is required")
    //@Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private Role role;
}