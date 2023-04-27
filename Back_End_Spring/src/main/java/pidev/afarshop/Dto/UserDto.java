package pidev.afarshop.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @JsonIgnore
    private List<Order1Dto> orders;
    @JsonIgnore
    Set<AnswerDto> answers;

    @JsonIgnore
    private Set<RatingDto> ratings;
    @JsonIgnore
    Set<ProductCommentDto> productComments;
    @JsonIgnore
    Set<ProductDto> products;
    @JsonIgnore
    Set<ProductLikeDto> productLikes;
    @JsonIgnore
    CartDto cart;
    @JsonIgnore
    Set<NotificationDto> notifications;

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        List<Order1> order1s = userDto.getOrders().stream()
                .map(Order1Dto::toEntity)
                .collect(Collectors.toList());
        Set<Answer> answerSet = userDto.getAnswers().stream()
                .map(AnswerDto::toEntity)
                .collect(Collectors.toSet());
        Set<Rating> ratingSet = userDto.getRatings().stream()
                .map(RatingDto::toEntity)
                .collect(Collectors.toSet());
        Set<ProductComment> productCommentSet = userDto.getProductComments().stream()
                .map(ProductCommentDto::toEntity)
                .collect(Collectors.toSet());
        Set<Product> productSet = userDto.getProducts().stream()
                .map(ProductDto::toEntity)
                .collect(Collectors.toSet());
        Set<ProductLike> productLikeSet = userDto.getProductLikes().stream()
                .map(ProductLikeDto::toEntity)
                .collect(Collectors.toSet());
        Cart cart1 = CartDto.toEntity(userDto.getCart());
        Set<Notification> notificationSet = userDto.getNotifications().stream()
                .map(NotificationDto::toEntity)
                .collect(Collectors.toSet());
        return User.builder()
                .UserId(userDto.getUserId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .address(userDto.getAddress())
                .dayOfBirth(userDto.getDayOfBirth())
                .cin(userDto.getCin())
                .telNum(userDto.getTelNum())
                .images(userDto.getImages())
                .roles(userDto.getRoles())
                .orders(order1s)
                .answers(answerSet)
                .ratings(ratingSet)
                .productComments(productCommentSet)
                .products(productSet)
                .productLikes(productLikeSet)
                .cart(cart1)
                .notifications(notificationSet)
                .build();
    }
    public static UserDto toDto(User user){
        if(user==null){
            //TODO EXCEPTION ERRROR
            return null;
        }
        List<Order1Dto> order1Dtos = user.getOrders().stream()
                .map(Order1Dto::toDto)
                .collect(Collectors.toList());
        Set<AnswerDto> answerDtos = user.getAnswers().stream()
                .map(AnswerDto::toDto)
                .collect(Collectors.toSet());
        Set<RatingDto> ratingDtos = user.getRatings().stream()
                .map(RatingDto::toDto)
                .collect(Collectors.toSet());
        Set<ProductCommentDto> productCommentDtos = user.getProductComments().stream()
                .map(ProductCommentDto::toDto)
                .collect(Collectors.toSet());
        Set<ProductDto> productDtos = user.getProducts().stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toSet());
        Set<ProductLikeDto> productLikeDtos = user.getProductLikes().stream()
                .map(ProductLikeDto::toDto)
                .collect(Collectors.toSet());
        CartDto cartDto =CartDto.toDto(user.getCart());
        Set<NotificationDto> notificationDtos = user.getNotifications().stream()
                .map(NotificationDto::toDto)
                .collect(Collectors.toSet());
        return  UserDto.builder()
                .UserId(user.getUserId())
                .firstname(user.getfirstname())
                .lastname(user.getlastname())
                .username(user.getUsername())
                .email(user.getemail())
                .password(user.getPassword())
                .address(user.getAddress())
                .dayOfBirth(user.getDayOfBirth())
                .cin(user.getCin())
                .telNum(user.getTelNum())
                .images(user.getImages())
                .roles(user.getRoles())
                .orders(order1Dtos)
                .answers(answerDtos)
                .ratings(ratingDtos)
              //  .productComments(productCommentDtos)
                .products(productDtos)
                .productLikes(productLikeDtos)
                .cart(cartDto)
                .notifications(notificationDtos)
                .build();
    }

}
