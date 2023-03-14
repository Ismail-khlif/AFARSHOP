package pidev.afarshop.Service.Delivery;
import org.springframework.data.repository.query.Param;
import pidev.afarshop.Entity.Delivery;
import pidev.afarshop.Entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IDeliveryService {
    List<Delivery> retrieveAllDeliveries();
    Delivery addDelivery(Delivery delivery, Long OrderId);
    void deleteDelivery(Long id);
    Delivery updateDelivery(Delivery delivery);
    User retrieveConnectedUser();
    List<Delivery> retrieveDeliveryByUser();

    String bestDeliveryMode(Date startDate, Date endDate);

}
