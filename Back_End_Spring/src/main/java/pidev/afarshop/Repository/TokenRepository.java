package pidev.afarshop.Repository;

import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("select t from Token t inner join t.user u "
            + "where u.UserId = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Long id);
    Optional<Token> findByToken(String token);
}

