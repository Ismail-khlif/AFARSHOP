package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.Query;
import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmailIgnoreCase(String email);
   /* @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    User findByEmail(String email);

    User findByResetPasswordToken(String token);*/
}