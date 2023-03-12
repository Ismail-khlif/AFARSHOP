package pidev.afarshop.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.QuizQuestion;

@Repository
public interface QuestionRepository extends JpaRepository<QuizQuestion, Long> {

}
