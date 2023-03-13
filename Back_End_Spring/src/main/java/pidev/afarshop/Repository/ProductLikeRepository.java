package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.ProductLike;

import java.util.Set;
@Repository
public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {


    @Query(value =" SELECT * from users u  INNER JOIN product_like p ON p.user_user_id = u.user_id ORDER BY count(*)",nativeQuery=true)
    public Set<Object> USer_order_by_Like ();
}
