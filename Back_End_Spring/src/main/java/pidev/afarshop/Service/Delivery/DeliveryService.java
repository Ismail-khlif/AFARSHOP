package pidev.afarshop.Service.Delivery;




import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

    @Service
    @Slf4j
    @AllArgsConstructor
    public class DeliveryService implements IDeliveryService {
        @Autowired
        Order1Repository order1Repository;
        @Autowired
        DeliveryRepository deliveryRepository;
        @Autowired
        HomeDeliveryRepository homeDeliveryRepository;
        @Autowired
        StoreDeliveryRepository storesDeliveryRepository;
        @Autowired
        UserRepository userRepository;

        @Override
        public List<Delivery> retrieveAllDeliveries() {
            return (List<Delivery>) deliveryRepository.findAll();
        }

        @Transactional
        public Delivery addDelivery(Delivery delivery, Long orderId) {

            Order1 order = order1Repository.findById(orderId).orElse(null);
            switch (delivery.getDeliveryMode())
            {
                case Home: {
                    HomeDelivery home = saveHomeDelivery(delivery);
                    delivery.setHomeDelivery(home);
                    home.setDelivery(delivery);
                    homeDeliveryRepository.save(home);
                    break;
                }
                case Store:{
                    StoreDelivery stores = saveStoresDelivery(delivery);
                    delivery.setStoreDelivery(stores);
                    stores.setDelivery(delivery);
                    storesDeliveryRepository.save(stores);
                    break;
                }
            }

            delivery.setCreationDate(Calendar.getInstance().getTime());
            delivery.setStatus(Status.On_hold);
            order.setDelivery(delivery);
            deliveryRepository.save(delivery);
            order1Repository.save(order);
            //System.out.println(orderId);
            //System.out.println(order.getCodePromo());
            //System.out.println(delivery);
            return delivery;
        }
        private HomeDelivery saveHomeDelivery(Delivery delivery){
            HomeDelivery home = delivery.getHomeDelivery();
            homeDeliveryRepository.save(home);
            return home;
        }
        private StoreDelivery saveStoresDelivery(Delivery delivery){
            StoreDelivery store = delivery.getStoreDelivery();
            storesDeliveryRepository.save(store);
            return store;
        }

        @Override
        public void deleteDelivery(Long id) {
            deliveryRepository.deleteById(id);
        }

        @Transactional
        public Delivery updateDelivery(Delivery delivery) {
            if(delivery.getDeliveryMode()==DeliveryMode.Home)
            {
                HomeDelivery home = updateHomeDelivery(delivery);
                delivery.setHomeDelivery(home);
            }
            if(delivery.getDeliveryMode()==DeliveryMode.Store)
            {
                StoreDelivery stores = updateStoresDelivery(delivery);
                delivery.setStoreDelivery(stores);
            }
            deliveryRepository.save(delivery);
            return delivery;
        }

        private HomeDelivery updateHomeDelivery(Delivery delivery){
            HomeDelivery home = delivery.getHomeDelivery();
            homeDeliveryRepository.save(home);
            return home;
        }
        private StoreDelivery updateStoresDelivery(Delivery delivery){
            StoreDelivery store = delivery.getStoreDelivery();
            storesDeliveryRepository.save(store);
            return store;
        }

        @Override
        public User retrieveConnectedUser(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User u=userRepository.retrieveUserByEmail(currentPrincipalName);
            return u;
        }

    @Override
    public List<Delivery> retrieveDeliveryByUser() {
            User user = retrieveConnectedUser();
            long Id = user.getUserId();
        List<Delivery> delivery = deliveryRepository.retrieveDeliveryByUser(Id);
        return delivery;
    }

        @Override
        public String bestDeliveryMode(Date startDate, Date endDate) {

                int homeNb = deliveryRepository.countHomeDeliveryByCreationDate(startDate, endDate);
                int storeNb = deliveryRepository.countStoreDeliveryByCreationDate(startDate, endDate);
                long homeIncome = homeNb * 7;
                long storeIncome = storeNb * 3;
                String best= "";
                if(homeIncome > storeIncome) {
                    best = "Home Delivery";
                }
                else {
                    best = "StoreDelivery";
                }
                return "The mode that earned us the most profit is "+ best + "\n store => " + storeIncome + " dt \n home => " + homeIncome + " dt";


        }


    }

