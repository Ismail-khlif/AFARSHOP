package pidev.afarshop.Service.Rating;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.afarshop.Entity.Rating;
import pidev.afarshop.Entity.Store;
import pidev.afarshop.Entity.User;
import pidev.afarshop.Repository.RatingRepository;
import pidev.afarshop.Repository.StoreRepository;
import pidev.afarshop.Repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServices implements IRatingServices {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StoreRepository storeRepository;


    @Override
    public Rating retrieveUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return ratingRepository.findById(userId).orElse(null );
    }

    @Override
    public void UserLikesStore(Long userId, Long storeId, Boolean isLiked) {
        User user = userRepository.findById(userId).orElse(null);
        Store store = storeRepository.findById(storeId).orElse(null);
        while (retrieveUserById(userId) ==null) {
            Rating rating = new Rating();
            rating.setUser(user);
            rating.setStore(store);
            rating.setLiked(isLiked);
            ratingRepository.save(rating);
        }
    }


    @Override
    public void ChangeStoreRating(Long ratingId, Boolean isLiked){
        Rating rating = ratingRepository.findById(ratingId).orElse(null);
        rating.setLiked(isLiked);
        ratingRepository.save(rating);

    }




}
