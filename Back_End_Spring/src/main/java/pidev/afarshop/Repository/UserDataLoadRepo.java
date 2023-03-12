package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.afarshop.Entity.UserDataLoad;

public interface UserDataLoadRepo extends JpaRepository<UserDataLoad, Long> {
}
