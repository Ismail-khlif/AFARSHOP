package pidev.afarshop.Service.Order;

import pidev.afarshop.Entity.Order;

import java.util.List;
import java.util.Map;

public interface IOrderServices {
    public Map<String, List<Order>> displayOrdersByProvider();
}

