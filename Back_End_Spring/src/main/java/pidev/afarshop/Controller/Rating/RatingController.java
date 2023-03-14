package pidev.afarshop.Controller.Rating;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.afarshop.Service.Rating.IRatingServices;

@RestController
@AllArgsConstructor
public class RatingController {

    @Autowired
    IRatingServices ratingServices;


    @PostMapping("/LikeStore/{UserId}/{storeId}")
    public void UserLikesStore(@PathVariable("UserId") Long UserId, @PathVariable ("storeId") Long storeId, @RequestParam Boolean isLiked) {
        ratingServices.UserLikesStore( UserId, storeId,isLiked);
    }

    @PutMapping("/ChangeStoreRating/{ratingId}")
    public void ChangeStoreRating(@PathVariable("ratingId") Long ratingId,@RequestParam Boolean isLiked){
        ratingServices.ChangeStoreRating(ratingId,isLiked);
    }

}
