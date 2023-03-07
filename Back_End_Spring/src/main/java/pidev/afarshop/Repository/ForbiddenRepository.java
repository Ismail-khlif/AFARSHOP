package pidev.afarshop.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Forbidden;
@Repository
public interface ForbiddenRepository extends CrudRepository<Forbidden, Long>{
}
