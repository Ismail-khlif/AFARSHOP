package pidev.afarshop.Service.Store;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pidev.afarshop.Service.Payement.SmsService;

import java.util.HashSet;
import java.util.List;

import java.util.Optional;

import java.util.Set;


@Service
@Slf4j
@AllArgsConstructor
@Validated
public class StoreService implements IStoreServices {


    private final String accountSid = "AC20887c66c515d0f7f341cb39c5c99ff7";
    private final String authToken = "a269039bc1779b0b2dc59260efb2c1b4";
    private final SmsService smsService;
    @Autowired
    private Environment environment;

    public void init() {
        String accountSid = environment.getProperty("accountSid");
        String authToken = environment.getProperty("authToken");

        try {
            Twilio.init(accountSid, authToken);
        } catch (TwilioException ex) {
            // log error
        }
    }
    @Autowired
    StoreRepository storeRepository;


    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RatingRepository ratingRepository;





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
        String fromNumber = environment.getProperty("+15747014044");
        String message = "Your new Store is added successfully!"+s.getStoreName() ;
        try {
            smsService.sendSms("+21650879536",message);
        } catch (TwilioException e) {
            // Gérer les erreurs d'envoi de SMS
        }
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

    @Override
    public List<Rating> getRatingByStoreId(Long storeId) {
        Store s = storeRepository.findById(storeId).orElse(null);
        return ratingRepository.findByStore(s);
    }
    // return (List<Rating>) ratingRepository.findByStore(s).stream().filter(rating -> rating.isLiked()== true);    }

    @Override
    public Integer nbLikes(Long storeId){
        List<Rating> ratings =getRatingByStoreId(storeId);
        Store s = storeRepository.findById(storeId).orElse(null);
        int nbLikes = 0;
        for (Rating rating:ratings){
            if(rating.isLiked()){
                nbLikes= nbLikes+1;
            }
        }
        s.setNbLikes(nbLikes);
        return nbLikes;
    }

    @Override
    public Integer nbDislikes (Long storeId){
        List<Rating> ratings = getRatingByStoreId(storeId);
        Store s = storeRepository.findById(storeId).orElse(null);
        int nbDislikes =0;
        for (Rating rating : ratings){
            if (!rating.isLiked()){
                nbDislikes = nbDislikes+1;
            }
        }
        s.setNbDislikes(nbDislikes);
        return nbDislikes;
    }

   // @Override
   // public double Score(Long storeId) {
      //  return 0;
    //}


    @Override
    public Store affectScore (Long storeId){
        Store store = storeRepository.findByStoreId(storeId);
        double sc = (nbLikes(storeId) *0.5)+ (nbDislikes(storeId)*0.3)+((nbLikes(storeId)+nbDislikes(storeId))*0.2);
        store.setScore(sc);
        return storeRepository.save(store);
    }

    @Override
    public Store findHighestScoredStore(){
        return storeRepository.findHighestScoredStore();

    }

    @Override
    public void StoreEvaluationByScore(){
        for(Store s : storeRepository.findAll()){
            if(s.getScore()>3.5){
                s.setEvaluation(Evaluation.TOP);
            }
            else
            if(s.getScore()>3){
                s.setEvaluation(Evaluation.MEDIUM);
            }
            else
                s.setEvaluation(Evaluation.LOW);
        }
    }
    @Override
    public Store affectEvaluation(Long storeId){
        Store store = storeRepository.findById(storeId).orElse(null);
        StoreEvaluationByScore();
        nbLikes(storeId);
        nbDislikes(storeId);
        return storeRepository.save(store);

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

    public List<Store> top5LikedStores(){
        List<Store> stores =(List<Store>) storeRepository.top5LikedStores();
        return stores;
    }


}