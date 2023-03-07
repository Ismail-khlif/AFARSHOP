package pidev.afarshop.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pidev.afarshop.Entity.ReactionC;

@Repository
public interface ReactionCRepository extends CrudRepository<ReactionC, Long> {

}