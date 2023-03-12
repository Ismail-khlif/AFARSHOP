package pidev.afarshop.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Reaction;


@Repository
public interface ReactionRepository  extends CrudRepository<Reaction, Long>{

}