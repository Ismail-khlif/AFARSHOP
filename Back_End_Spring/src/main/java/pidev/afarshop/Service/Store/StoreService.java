package pidev.afarshop.Service.Store;

import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class StoreService implements ICRUDService<Store,Long> , IStoreServices {
    StoreRepository storeRepository;
    UserRepository userRepository;
    QuizzRepository quizzRepository;
    @Override
    public List<Store> findAll() {

        return storeRepository.findAll();
    }

    @Override
    public Store retrieveItem(Long idItem) {
        return storeRepository.findById(idItem).get();
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
    public void createQuizz(Quiz Q, Long idCourse,Long idUser)  {
        Store c = storeRepository.findById(idCourse).get();
        User usr = userRepository.findById(idUser).get();

        Set<Quiz> quiz = new HashSet<>();
        quiz.add(Q);
        c.getQuiz().add(Q);

        storeRepository.flush();
        quizzRepository.save(Q);

    }

}