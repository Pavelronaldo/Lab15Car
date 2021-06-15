package nnnocturn.web.service.impl;

import nnnocturn.db.OrderStatus;
import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Order;
import nnnocturn.db.entity.User;
import nnnocturn.db.repository.OrderRepository;
import nnnocturn.web.bean.AcceptCarBean;
import nnnocturn.web.bean.MakeOrderBean;
import nnnocturn.web.bean.PayOrderBean;
import nnnocturn.web.bean.TreatmentOrderBean;
import nnnocturn.web.service.OrderService;

import java.sql.Timestamp;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository ORDER_REPOSITORY;

    public OrderServiceImpl(OrderRepository ORDER_REPOSITORY) {
        this.ORDER_REPOSITORY = ORDER_REPOSITORY;
    }

    /**
     * Set status
     *
     * @param treatmentOrderBean order to be set
     */
    @Override
    public void setAcceptedStatus(TreatmentOrderBean treatmentOrderBean) {
        ORDER_REPOSITORY.updateOrder(getEntity(treatmentOrderBean));
    }

    /**
     * Set returned status
     *
     * @param order to be returned status
     */
    @Override
    public void setReturnedStatus(Order order) {
        ORDER_REPOSITORY.updateOrderStatus(getEntity(order));
    }

    /**
     * Reject status
     *
     * @param treatmentOrderBean order to be reject
     */
    @Override
    public void setRejectedStatus(TreatmentOrderBean treatmentOrderBean) {
        ORDER_REPOSITORY.updateOrder(getEntity(treatmentOrderBean));
    }

    /**
     * Set paid status
     *
     * @param payOrderBean to be set paid status
     */
    @Override
    public void setPaidStatus(PayOrderBean payOrderBean) {
        ORDER_REPOSITORY.updateOrderStatus(getEntity(payOrderBean));
    }

    /**
     * Set closed status
     *
     * @param acceptCarBean to be closed status
     */
    @Override
    public void setClosedStatus(AcceptCarBean acceptCarBean) {
        ORDER_REPOSITORY.updateOrderStatus(getEntity(acceptCarBean));
    }

    /**
     * Get list order DTO by user
     *
     * @param user to be get list order
     * @return list order DTO
     */
    @Override
    public List<OrderDTO> getOrderDTOByUser(User user) {
        return ORDER_REPOSITORY.getOrderDTOByUser(user);
    }

    /**
     * Get list order DTO
     *
     * @return list order DTO
     */
    @Override
    public List<OrderDTO> getReturningOrdersDTO() {
        Order order = new Order();
        order.setIdStatus(OrderStatus.RETURNING.getNumber());
        return ORDER_REPOSITORY.getOrdersDTOByStatus(order);
    }

    /**
     * Get list order DTO
     *
     * @return list order DTO
     */
    @Override
    public List<OrderDTO> getConsideringOrdersDTO() {
        Order order = new Order();
        order.setIdStatus(OrderStatus.CONSIDERING.getNumber());
        return ORDER_REPOSITORY.getOrdersDTOByStatus(order);
    }

    /**
     * Create new order
     *
     * @param makeOrderBean new order
     */
    @Override
    public void createAnOrder(MakeOrderBean makeOrderBean) {
        ORDER_REPOSITORY.createAnOrder(getEntity(makeOrderBean));
    }

    /**
     * Get entity order
     *
     * @param makeOrderBean order
     * @return car object
     */
    private Order getEntity(MakeOrderBean makeOrderBean) {
        Order order = new Order();
        order.setIdCar(makeOrderBean.getIdCar());
        order.setWithDriver(makeOrderBean.isWithDriver());
        order.setFromDate(new Timestamp(makeOrderBean.getFrom()));
        order.setToDate(new Timestamp(makeOrderBean.getTo()));
        order.setIdUser(makeOrderBean.getIdUser());
        return order;
    }

    /**
     * Get entity order
     *
     * @param payOrderBean order
     * @return car object
     */
    private Order getEntity(PayOrderBean payOrderBean) {
        Order order = new Order();
        order.setId(payOrderBean.getIdOrder());
        order.setIdStatus(OrderStatus.PAID.getNumber());
        return order;
    }

    /**
     * Get entity order
     *
     * @param treatmentOrderBean order
     * @return order entity
     */
    private Order getEntity(TreatmentOrderBean treatmentOrderBean) {
        Order order = new Order();
        order.setId(treatmentOrderBean.getId());
        order.setIdStatus(treatmentOrderBean.getStatus().getNumber());
        order.setReasonDeny(treatmentOrderBean.getComment());
        return order;
    }

    /**
     * Get entity order
     *
     * @param order to be get
     * @return order entity
     */
    private Order getEntity(Order order) {
        order.setIdStatus(OrderStatus.RETURNING.getNumber());
        return order;
    }

    /**
     * Get entity order
     *
     * @param acceptCarBean to be get
     * @return order entity
     */
    private Order getEntity(AcceptCarBean acceptCarBean) {
        Order order = new Order();
        order.setId(acceptCarBean.getId());
        order.setIdStatus(OrderStatus.CLOSED.getNumber());
        return order;
    }
}
