package nnnocturn.web.listener;

import nnnocturn.db.DBManager;
import nnnocturn.db.dao.*;
import nnnocturn.db.repository.*;
import nnnocturn.db.repository.impl.*;
import nnnocturn.util.Constant;
import nnnocturn.util.Util;
import nnnocturn.web.command.Command;
import nnnocturn.web.service.BillService;
import nnnocturn.web.service.CarService;
import nnnocturn.web.service.OrderService;
import nnnocturn.web.service.UserService;
import nnnocturn.web.service.impl.BillServiceImpl;
import nnnocturn.web.service.impl.CarServiceImpl;
import nnnocturn.web.service.impl.OrderServiceImpl;
import nnnocturn.web.service.impl.UserServiceImpl;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Command.setContext(servletContext);

        DBManager dbManager = DBManager.getInstance();
        UserService userService = getUserService(dbManager);
        BillService billService = getBillService(dbManager);
        CarService carService = getCarService(dbManager);
        OrderService orderService = getOrderService(dbManager);

        Util util = new Util();
        Validator validator = new Validator(util);


        servletContext.setAttribute(Constant.VALIDATOR, validator);
        servletContext.setAttribute(Constant.UTIL, util);
        servletContext.setAttribute(Constant.USER_SERVICE_MANAGER,userService);
        servletContext.setAttribute(Constant.BILL_SERVICE_MANAGER,billService);
        servletContext.setAttribute(Constant.CAR_SERVICE_MANAGER,carService);
        servletContext.setAttribute(Constant.ORDER_SERVICE_MANAGER, orderService);
    }



    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private UserService getUserService(DBManager dbManager) {
        UserDAORepository userDAORepository = new UserDAORepository(dbManager);
        UserRepository userRepository = new UserRepositoryImpl(userDAORepository, dbManager);
        return new UserServiceImpl(userRepository);
    }

    private CarService getCarService(DBManager dbManager) {
        CarDAORepository carDAORepository = new CarDAORepository(dbManager);
        BrandDAORepository brandDAORepository = new BrandDAORepository(dbManager);
        CategoryDAORepository categoryDAORepository = new CategoryDAORepository(dbManager);

        CarRepository carRepository = new CarRepositoryImpl(dbManager, carDAORepository);
        BrandRepository brandRepository = new BrandRepositoryImpl(dbManager, brandDAORepository);
        CategoryRepository categoryRepository = new CategoryRepositoryImpl(dbManager, categoryDAORepository);
        return new CarServiceImpl(carRepository, brandRepository, categoryRepository);
    }

    private OrderService getOrderService(DBManager dbManager) {
        OrderDAORepository orderDAORepository = new OrderDAORepository(dbManager);
        OrderRepository orderRepository = new OrderRepositoryImpl(orderDAORepository, dbManager);
        return new OrderServiceImpl(orderRepository);
    }

    private BillService getBillService(DBManager dbManager) {
        BillDAORepository billDAORepository = new BillDAORepository(dbManager);
        BillRepository billRepository = new BillRepositoryImpl(dbManager, billDAORepository);
        return new BillServiceImpl(billRepository);
    }
}
