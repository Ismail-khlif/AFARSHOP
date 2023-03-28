package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Quiz;
import pidev.afarshop.Entity.QuizQuestion;
import pidev.afarshop.Entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    Long quizId;

    String theme;
    @JsonIgnore
    Set<QuizQuestionDto> questions; // Questions related to quizz

    public static Quiz toEntity(QuizDto quizDto) {
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
    public static QuizDto toDto(Quiz quiz){
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
