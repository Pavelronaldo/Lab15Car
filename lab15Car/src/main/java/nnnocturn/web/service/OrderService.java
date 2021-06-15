package nnnocturn.web.service;

import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Order;
import nnnocturn.db.entity.User;
import nnnocturn.web.bean.AcceptCarBean;
import nnnocturn.web.bean.MakeOrderBean;
import nnnocturn.web.bean.PayOrderBean;
import nnnocturn.web.bean.TreatmentOrderBean;

import java.util.List;

public interface OrderService {

    void setAcceptedStatus(TreatmentOrderBean treatmentOrderBean);

    void setRejectedStatus(TreatmentOrderBean treatmentOrderBean);

    void setPaidStatus(PayOrderBean payOrderBean);

    void setReturnedStatus(Order order);

    void setClosedStatus(AcceptCarBean acceptCarBean);

    List<OrderDTO> getOrderDTOByUser(User user);

    List<OrderDTO> getReturningOrdersDTO();

    List<OrderDTO> getConsideringOrdersDTO();

    void createAnOrder(MakeOrderBean makeOrderBean);
}
