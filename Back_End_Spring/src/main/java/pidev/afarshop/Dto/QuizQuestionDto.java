package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuizQuestionDto {

    Long questionId;

    String question;

    int score;

    Set<AnswerDto> answers; // Answers related to questions
}
