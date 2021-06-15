package nnnocturn.db.repository;

import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Order;
import nnnocturn.db.entity.User;

import java.util.List;

public interface OrderRepository {

    void updateOrder(Order order) ;

    void updateOrderStatus(Order order);

    List<OrderDTO> getOrderDTOByUser(User user);

    List<OrderDTO> getOrdersDTOByStatus(Order order);

    void createAnOrder(Order order);
}
