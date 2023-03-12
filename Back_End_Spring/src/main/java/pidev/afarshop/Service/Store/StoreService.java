package pidev.afarshop.Service.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import java.util.Optional;

import java.util.Set;


@Service
@Slf4j
@AllArgsConstructor
//@Validated
public class StoreService implements  IStoreServices {

    @Autowired
    StoreRepository storeRepository;


    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;




    QuizzRepository quizzRepository;

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

    public Store addStore (Store store)   {
        Store s = storeRepository.save(store);
        findCategoryToStore(s.getStoreId());
        return s;

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


    @Override
    public void affectStoreToCategory(Long storeId, Long categoryId) {
        Category c = categoryRepository.findById(categoryId).orElse(null);
        Store s = storeRepository.findById(storeId).orElse(null);
        s.setCategory(c);
        c.getStores().add(s);

        categoryRepository.save(c);
        storeRepository.save(s);

    }



    @Override
    public void findCategoryToStore(Long storeId) {
        String x="",str1="",str2="";
        int i;
        int id=-1;
        Store s = storeRepository.findById(storeId).orElse(null);
        List<Category> categorys = (List<Category>) categoryRepository.findAll();
        for(Category c: categorys)
        {
            ////dictionary youfa b "-"
            if(c.getDictionary().endsWith("-"))
            {
                x = c.getDictionary();
                System.out.println("xxxxxxxxxxxxxx : " + x);
            }
            else
            {
                x = c.getDictionary()+"-";
                System.out.println("xxxxxxxxxxxxxx : " + x);
            }
            ////////extraction des mots et comparaison
            do
            {
                i = x.indexOf("-");

                str1 = x.substring(0, i);
                str2 = x.replace(str1+"-","");
                x=str2;
                System.out.println("str1 : " + str1);
                System.out.println("str2 : " + str2);
                System.out.println("x : " + x);
                System.out.println(s.getStoreDescription().contains(str1));
            }
            while(s.getStoreDescription().contains(str1)== false && x.isEmpty()==false);
            if(s.getStoreDescription().contains(str1))
            {

                id = c.getCategoryId().intValue();
                break;
            }

        }
        Store ss = storeRepository.findById(storeId).orElse(null);
        if(ss.getStoreDescription().contains(str1)){
            affectStoreToCategory(storeId, Long.valueOf(id));
        }else
        {
            affectStoreToCategory(storeId, categoryRepository.findByName("Other").getCategoryId());
        }

        System.out.println(ss.getCategory().getName());



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