package nnnocturn.db.repository.impl;

import org.apache.log4j.Logger;
import nnnocturn.db.DBManager;
import nnnocturn.db.dao.OrderDAORepository;
import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Order;
import nnnocturn.db.entity.User;
import nnnocturn.db.repository.OrderRepository;
import nnnocturn.exception.DBException;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final Logger LOGGER = Logger.getLogger(OrderRepositoryImpl.class);

    private final OrderDAORepository orderDAORepository;

    private final DBManager dbManager;

    public OrderRepositoryImpl(OrderDAORepository ORDER_DAO_REPOSITORY, DBManager DB_MANAGER) {
        this.orderDAORepository = ORDER_DAO_REPOSITORY;
        this.dbManager = DB_MANAGER;
    }
    /**
     * Update order
     *
     * @param order object which need update
     */
    @Override
    public void updateOrder(Order order) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update order: " + order);
                return orderDAORepository.updateOrder(order);
            } catch (DBException e) {
                LOGGER.error("Cannot update order " + e);
            }
            return false;
        });
    }

    /**
     * Update order status
     *
     * @param order object which need update status
     */
    @Override
    public void updateOrderStatus(Order order) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update status order : " + order);
                return orderDAORepository.updateOrderStatus(order);
            } catch (DBException e) {
                LOGGER.error("Cannot update status order " + e);
            }
            return false;
        });
    }

    /**
     * @param user to get by list order DTO
     * @return list orderDTO by user
     */
    @Override
    public List<OrderDTO> getOrderDTOByUser(User user) {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with order DTO by user was get");
                return orderDAORepository.getOrderDTOByUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot get list order DTO by user " + e);
                return null;
            }
        });
    }

    /**
     * @param order to get by list order DTO
     * @return list orderDTO by order
     */
    @Override
    public List<OrderDTO> getOrdersDTOByStatus(Order order) {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with order DTO by status was get");
                return orderDAORepository.getOrdersDTOByStatus(order);
            } catch (DBException e) {
                LOGGER.error("Cannot get list order DTO by status " + e);
                return null;
            }
        });
    }

    /**
     * Create new order
     * @param order object which need create
     */
    @Override
    public void createAnOrder(Order order) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Create order: " + order);
                return orderDAORepository.insertOrder(order);
            } catch (DBException e) {
                LOGGER.error("Cannot create new order " + e);
            }
            return false;
        });
    }
}
