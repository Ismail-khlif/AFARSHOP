package pidev.afarshop.Service.Delivery;
import pidev.afarshop.Entity.Delivery;
import java.util.List;
public interface IDeliveryService {
    List<Delivery> retrieveAllDeliveries();
    Delivery addDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    Delivery updateDelivery(Delivery delivery);
}
