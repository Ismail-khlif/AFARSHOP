package pidev.afarshop.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

  /*  Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmailIgnoreCase(String email);*/

   // User findByLogin(String Login) ;

    

 @Query("SELECT u FROM User u  WHERE u.firstname LIKE %:firstname%")
  List<User> FindByfirstname(@Param("firstname") String firstname);

}

