package pidev.afarshop.Service.Store;

import pidev.afarshop.Entity.*;

import java.util.List;

public interface IStoreServices {
    public List<Store> findAll();
    public Store retrieveItem(Long storeId);
    public Store addStore (Store store);
    public void delete(Long storeId);
    public Store update(Store store);

    public Store findStoreByName(String storeName);

    public void findCategoryToStore(Long storeId);
    public void affectStoreToCategory(Long storeId, Long categoryId);

    public Integer nbLikes(Long storeId);
    public Integer nbDislikes (Long storeId);
    //public double Score(Long storeId);

    public Store affectScore (Long storeId);


    public List<Rating> getRatingByStoreId(Long storeId);

    public Store findHighestScoredStore();

    public void StoreEvaluationByScore();
    public Store affectEvaluation(Long storeId);

    public List<Store> top5LikedStores();

}
