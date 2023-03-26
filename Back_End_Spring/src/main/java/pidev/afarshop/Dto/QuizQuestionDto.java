package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Answer;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.QuizQuestion;
import pidev.afarshop.Entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class QuizQuestionDto {

    Long questionId;

    String question;

    int score;

    Set<AnswerDto> answers; // Answers related to questions
    public static QuizQuestion toEntity(QuizQuestionDto quizQuestionDto) {
        if (quizQuestionDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        Set<Answer> answerss = quizQuestionDto.getAnswers().stream()
                .map(AnswerDto::toEntity)
                .collect(Collectors.toSet());
        return QuizQuestion.builder()
                .questionId(quizQuestionDto.getQuestionId())
                .question(quizQuestionDto.getQuestion())
                .score(quizQuestionDto.getScore())
                .answers(answerss)
                .build();
    }
    public static QuizQuestionDto toDto(QuizQuestion quizQuestion){
        if(quizQuestion==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        Set<AnswerDto> answerDtos = quizQuestion.getAnswers().stream()
                .map(AnswerDto::toDto)
                .collect(Collectors.toSet());
        return  QuizQuestionDto.builder()
                .questionId(quizQuestion.getQuestionId())
                .question(quizQuestion.getQuestion())
                .score(quizQuestion.getScore())
                .answers(answerDtos)
                .build();
    }

}
