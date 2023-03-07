package pidev.afarshop.Service.Rating;

import pidev.afarshop.Entity.Rating;

public interface IRatingServices {
    public void UserLikesStore ( Long userId, Long storeId,Boolean isLiked );
    public Rating retrieveUserById(Long userId);
    public void ChangeStoreRating(Long ratingId, Boolean isLiked);
    //public void UserDislikesStore(Long ratingId);
}