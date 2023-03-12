package pidev.afarshop.Service.Order1;


import pidev.afarshop.Entity.Order1;

import java.util.List;
import java.util.Map;

public interface IOrderServices {
    public Map<String, List<Order1>> displayOrdersByProvider();
}


