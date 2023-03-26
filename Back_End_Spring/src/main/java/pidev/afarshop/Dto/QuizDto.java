package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Quiz;
import pidev.afarshop.Entity.QuizQuestion;
import pidev.afarshop.Entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class QuizDto {

    Long quizId;

    String theme;

    Set<QuizQuestionDto> questions; // Questions related to quizz

    public Quiz toEntity(QuizDto quizDto) {
        if (quizDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        Set<QuizQuestion> quizzes = quizDto.getQuestions().stream()
                .map(QuizQuestionDto::toEntity)
                .collect(Collectors.toSet());
        return Quiz.builder()
                .quizId(quizDto.getQuizId())
                .theme(quizDto.getTheme())
                .questions(quizzes)
                .build();
    }
    public QuizDto toDto(Quiz quiz){
        if(quiz==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        Set<QuizQuestionDto> quizDtos = quiz.getQuestions().stream()
                .map(QuizQuestionDto::toDto)
                .collect(Collectors.toSet());
        return  QuizDto.builder()
                .quizId(quiz.getQuizId())
                .theme(quiz.getTheme())
                .questions(quizDtos)
                .build();
    }
}
