package pidev.afarshop.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
