package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.Query;
import pidev.afarshop.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findBystoreName(String storeName);

    @Query("SELECT s.storeId,s.storeName , COUNT(s.storeId) FROM Store s , Rating r WHERE s.storeId = r.store.storeId AND r.isLiked= true GROUP BY s.storeId, s.storeName")
    List<Store> top5LikedStores();
}
