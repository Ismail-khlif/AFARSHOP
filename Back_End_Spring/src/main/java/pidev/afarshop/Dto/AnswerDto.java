package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Answer;
import pidev.afarshop.Entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class AnswerDto {

    Long answerId;

    String answerContent;

    boolean isCorrect;

    Set<UserDto> users;

    public static AnswerDto toDto(Answer answer){
        if(answer==null){

        }
        Set<UserDto> userDtos = answer.getUsers().stream()
                .map(UserDto::toDto)
                .collect(Collectors.toSet());
        return AnswerDto.builder()
                .answerId(answer.getAnswerId())
                .answerContent(answer.getAnswerContent())
                .isCorrect(answer.isCorrect())
                .users(userDtos)
                .build();
    }

    public static Answer toEntity(AnswerDto answerDto){
        if (answerDto ==null) {
            //TODO ERROR EXCEPTION
            return  null;
        }
        Set<User> users = answerDto.getUsers().stream()
                .map(UserDto::toEntity)
                .collect(Collectors.toSet());
        return Answer.builder()
                .answerId(answerDto.getAnswerId())
                .answerContent(answerDto.getAnswerContent())
                .isCorrect(answerDto.isCorrect())
                .users(users)
                .build();
    }
}
