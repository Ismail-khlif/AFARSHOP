package pidev.afarshop.Service.Delivery;




import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

    @Service
    @Slf4j
    @AllArgsConstructor
    public class DeliveryService implements IDeliveryService {
        @Autowired
        DeliveryRepository deliveryRepository;
        @Autowired
        HomeDeliveryRepository homeDeliveryRepository;
        @Autowired
        StoreDeliveryRepository storesDeliveryRepository;

        @Override
        public List<Delivery> retrieveAllDeliveries() {
            return (List<Delivery>) deliveryRepository.findAll();
        }

        @Transactional
        public Delivery addDelivery(Delivery delivery) {
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
            deliveryRepository.save(delivery);
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


    }

