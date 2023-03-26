package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.Order1;
import pidev.afarshop.Entity.Role;
import pidev.afarshop.Entity.User;

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

    private List<Order1Dto> orders;

    Set<AnswerDto> answers;


    private Set<RatingDto> ratings;

    Set<ProductCommentDto> productComments;

    Set<ProductDto> products;

    Set<ProductLikeDto> productLikes;

    CartDto cart;

    Set<NotificationDto> notifications;

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            //TODO EXCEPTION ERRROR
            return null;
        }
        List<Order1> order1s = userDto.getOrders().stream()
                .map(Order1Dto::toEntity)
                .collect(Collectors.toList());
        return User.builder()
                .UserId(userDto.getUserId())
                .uid(userDto.getUid())
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
                .answers(userDto.getAnswers())
                .ratings(userDto.getRatings())
                .productComments(userDto.getProductComments())
                .products(userDto.getProducts())
                .productLikes(userDto.getProductLikes())
                .cart(userDto.getCart())
                .notifications(userDto.getNotifications())
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
        return  UserDto.builder()
                .UserId(user.getUserId())
                .uid(user.getUid())
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
                .answers(user.getAnswers())
                .ratings(user.getRatings())
                .productComments(user.getProductComments())
                .products(user.getProducts())
                .productLikes(user.getProductLikes())
                .cart(user.getCart())
                .notifications(user.getNotifications())
                .build();
    }

}
