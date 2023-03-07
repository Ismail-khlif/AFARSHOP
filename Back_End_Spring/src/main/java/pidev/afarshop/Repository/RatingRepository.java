package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

}
