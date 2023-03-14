package pidev.afarshop.Service.Rating;

import pidev.afarshop.Entity.Rating;

public interface IRatingServices {
    public void UserLikesStore ( Long UserId, Long storeId,Boolean isLiked );
    public Rating retrieveUserById(Long UserId);
    public void ChangeStoreRating(Long ratingId, Boolean isLiked);
   // public Integer nbLikes(Long storeId);
    //public Integer nbDislikes (Long storeId);
    //public double Score(Long storeId);
}