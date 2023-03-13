package pidev.afarshop.Service.Quiz;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Answer;
import pidev.afarshop.Entity.Quiz;
import pidev.afarshop.Entity.QuizQuestion;

import java.util.*;

@Service
public interface QuizService {
    public void addQuestionToQuiz(QuizQuestion q, Long quizId);
    public void addListQuestionsToQuiz(Set<QuizQuestion> questions, Long quizId);
    public void removeQuestion(Long questionId,Long quizId);
    public void editQuestion(QuizQuestion question,Long questionId);
    public void addAnswersToQuestion(Set<Answer> answers, Long questionId);
    public void setCorrectAnswer(Long answerId);
    public Answer answerQuizQuestion(Long idUser,Long idAnswer);
    public int calculScore(Long idUser,Long idQuiz);

    public Quiz getQuiz(Long idQuiz);

    public Set<QuizQuestion> getQuestionsByQuizzId(long quizId);
    public Set<Answer> getAnswersByQuestionId(long questionId);

}
