package pidev.afarshop.Service.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
//@Validated
public class StoreService implements  IStoreServices {

    @Autowired
    StoreRepository storeRepository;


    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store retrieveItem(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isPresent()){
            return store.get();
        } else {
            throw new RuntimeException("Store not found with id " + storeId);
        }
    }

    @Override
    public Store add(Store store) {

        return storeRepository.save(store);
    }

    @Override
    public void delete(Long storeId) {

        storeRepository.deleteById(storeId);
    }

    @Override
    public Store update(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store findStoreByName(String storeName) {
        return storeRepository.findBystoreName(storeName);
    }
}