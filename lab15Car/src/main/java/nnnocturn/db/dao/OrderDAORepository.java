package nnnocturn.db.dao;

import org.apache.log4j.Logger;
import nnnocturn.db.DBManager;
import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Order;
import nnnocturn.db.entity.User;
import nnnocturn.exception.DBException;
import nnnocturn.util.DBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static nnnocturn.db.DBManager.close;

public class OrderDAORepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(OrderDAORepository.class);

    public OrderDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Update order
     *
     * @param order which update
     * @return true if update was completed
     * @throws DBException
     */
    public boolean updateOrder(Order order) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            LOGGER.info("ORDER: "+order);
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_ORDER);
            int i = 1;
            preparedStatement.setLong(i++, order.getIdStatus());
            preparedStatement.setString(i++, order.getReasonDeny());
            preparedStatement.setLong(i, order.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Order with id " + order.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update car ", e);
            throw new DBException("Unable to connect", e);

        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    /**
     * Update order which needs a status update
     *
     * @param order which update
     * @return true if update was completed
     * @throws DBException
     */
    public boolean updateOrderStatus(Order order) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int i = 1;
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_ORDER_STATUS);
            preparedStatement.setLong(i++, order.getIdStatus());
            preparedStatement.setLong(i, order.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Order with id " + order.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update order ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    /**
     * Get list order DTO by user from db
     *
     * @param user which needs get order DTO
     * @return list order DTO
     * @throws DBException
     */
    public List<OrderDTO> getOrderDTOByUser(User user) throws DBException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_GET_ORDER_DTO);
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDTO orderDTO = extractOrderDTO(resultSet);
                orderDTOList.add(orderDTO);
            }
            connection.commit();
            LOGGER.info("Order DTO by status: " + orderDTOList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert order DTO list by user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return orderDTOList;
    }

    /**
     * Get list order by status DTO from db
     *
     * @param order which needs get status DTO
     * @return list order DTO
     * @throws DBException
     */
    public List<OrderDTO> getOrdersDTOByStatus(Order order) throws DBException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_MANAGER_GET_ORDER_DTO);
            preparedStatement.setLong(1, order.getIdStatus());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDTOList.add(extractOrderDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Order with id " + order.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update order ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return orderDTOList;
    }

    /**
     * Insert an object of order class.
     *
     * @param order which needs to be insert
     * @return true if order was insert
     * @throws DBException
     */
    public boolean insertOrder(Order order)
            throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_INSERT_ORDER);
            int i = 1;
            preparedStatement.setBoolean(i++, order.isWithDriver());
            preparedStatement.setTimestamp(i++, order.getFromDate());
            preparedStatement.setTimestamp(i++, order.getToDate());
            preparedStatement.setLong(i++, order.getIdCar());
            preparedStatement.setLong(i, order.getIdUser());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(i + " " + order.getId());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert order ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    /**
     * Extracts a order DTO entity from the result set
     *
     * @param resultSet from which a order DTO entity will be extracted.
     * @return order DTO entity
     * @throws SQLException
     */
    private OrderDTO extractOrderDTO(ResultSet resultSet) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(resultSet.getLong("id_order"));
        orderDTO.setFirstName(resultSet.getString("firstname"));
        orderDTO.setLastName(resultSet.getString("lastname"));
        orderDTO.setModel(resultSet.getString("model"));
        orderDTO.setDriver(resultSet.getBoolean("driver"));
        orderDTO.setFromDate(resultSet.getTimestamp("fromdate"));
        orderDTO.setToDate(resultSet.getTimestamp("todate"));
        orderDTO.setStatus(resultSet.getString("status"));
        orderDTO.setReasonDeny(resultSet.getString("reasondeny"));
        orderDTO.setCost(resultSet.getDouble("cost"));
        return orderDTO;
    }
}
