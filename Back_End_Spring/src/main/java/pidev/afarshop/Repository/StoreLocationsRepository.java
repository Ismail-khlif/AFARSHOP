package pidev.afarshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.StoreLocations;
@Repository
public interface StoreLocationsRepository extends JpaRepository<StoreLocations,Long> {
}
