package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Role;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDto {

    private long UserId;

    private String uid;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String password;

    private String address;

    private Date dayOfBirth;

    private String cin;

    private String telNum;

    private byte[] images;

    private Role roles;

    //private List<Token> tokens;

    private List<Order1Dto> orders;

    Set<AnswerDto> answers;


    private Set<RatingDto> ratings;

    Set<ProductCommentDto> productComments;

    Set<ProductDto> products;

    Set<ProductLikeDto> productLikes;

    CartDto cart;

    Set<NotificationDto> notifications;

}
