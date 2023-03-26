package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuizDto {

    Long quizId;

    String theme;

    Set<QuizQuestionDto> questions; // Questions related to quizz
}
