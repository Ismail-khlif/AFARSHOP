package pidev.afarshop.Service.StoreLocation;

import pidev.afarshop.Dto.ProductDto;
import pidev.afarshop.Dto.StoreLocationsDto;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Service.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StoreLocationService implements ICRUDService<StoreLocations, Long>, IStoreLocationService{
    StoreLocationsRepository storeLocationsRepository;
    @Override
    public List<StoreLocations> findAll() {

        return storeLocationsRepository.findAll();
    }

    @Override
    public StoreLocations retrieveItem(Long idStoreLocationService) {
        return storeLocationsRepository.findById(idStoreLocationService).get();
    }

    @Override
    public StoreLocationsDto retrieveItema(Long idStoreLocationService) {
        //return storeLocationsRepository.findById(idStoreLocationService).get();
        Optional<StoreLocations>storeLocations=this.storeLocationsRepository.findById(idStoreLocationService);

        return StoreLocationsDto.toDto(storeLocations.get());

    }

    @Override
    public StoreLocations add(StoreLocations storeLocationService) {

        return storeLocationsRepository.save(storeLocationService);
    }

    @Override
    public void delete(Long cartId) {

        storeLocationsRepository.deleteById(cartId);
    }

    @Override
    public StoreLocations update(StoreLocations storeLocationService) {
        return storeLocationsRepository.save(storeLocationService);
    }

}
