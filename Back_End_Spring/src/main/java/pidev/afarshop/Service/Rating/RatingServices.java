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
    public Rating retrieveUserById(Long UserId) {
        User user = userRepository.findById(UserId).orElse(null);
        return ratingRepository.findById(UserId).orElse(null );
    }


    @Override
    public void UserLikesStore(Long UserId, Long storeId, Boolean isLiked) {
        User user = userRepository.findById(UserId).orElse(null);
        Store store = storeRepository.findById(storeId).orElse(null);
        if (retrieveUserById(UserId) == null) {
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
   /* @Override
    public Integer nbLikes(Long storeId){
        Rating rating = ratingRepository.findById(storeId).orElse(null);
        int nbLikes = 0;
        if(rating.isLiked()== true){
            nbLikes= nbLikes+1;
        }
        return nbLikes;
    }

    public Integer nbDislikes (Long storeId){
        Rating rating = ratingRepository.findById(storeId).orElse(null);
        int nbDislikes =0;
        if (rating.isLiked()== false){
            nbDislikes = nbDislikes+1;
        }
        return nbDislikes;

    }
    @Override
    public double Score(Long storeId ){
        Rating rating = ratingRepository.findById(storeId).orElse(null);
        double score = 0;
        score = (nbDislikes(storeId) *0.5 ) + (nbDislikes(storeId) *0.3)+ ((nbLikes(storeId)+nbDislikes(storeId))*0.2);
        return score;

    }*/




}
