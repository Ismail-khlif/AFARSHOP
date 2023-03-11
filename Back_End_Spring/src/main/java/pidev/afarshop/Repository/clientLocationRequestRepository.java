package pidev.afarshop.Repository;
import pidev.afarshop.Entity.ClientLocationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clientLocationRequestRepository  extends JpaRepository<ClientLocationRequest, Long> {
}