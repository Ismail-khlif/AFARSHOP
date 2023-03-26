package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Answer;

import java.util.Set;
@Builder
@Data
public class AnswerDto {

    Long answerId;

    String answerContent;

    boolean isCorrect;

    Set<UserDto> users;

    public  AnswerDto fromEntity(Answer answer){
        if(answer==null){

        }
        return AnswerDto.builder()
                .answerId(answer.getAnswerId())
                .answerContent(answer.getAnswerContent())
                .isCorrect(answer.isCorrect())
                .users(answer.getUsers())
                .build();
    }

    public Answer toEntity(AnswerDto answerDto){
        if (answerDto ==null) {
            //TODO ERROR EXCEPTION
            return  null;
        }
        return Answer.builder()
                .answerId(answerDto.getAnswerId())
                .answerContent(answerDto.getAnswerContent())
                .isCorrect(answerDto.isCorrect())
                .users(answerDto.getUsers())
                .build();
    }
}
