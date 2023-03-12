package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.afarshop.Entity.Payment;
import pidev.afarshop.Entity.ProductComment;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Long> {
}
