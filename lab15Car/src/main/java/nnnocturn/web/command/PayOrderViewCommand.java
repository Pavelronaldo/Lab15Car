package nnnocturn.web.command;

import nnnocturn.db.dto.OrderDTO;
import nnnocturn.db.entity.Bill;
import nnnocturn.db.entity.User;
import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.web.service.BillService;
import nnnocturn.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PayOrderViewCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        User user = (User) request.getSession().getAttribute(Constant.USER);
        OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
        List<OrderDTO> orderDTOList = orderService.getOrderDTOByUser(user);
        List<Bill> bills = new ArrayList<>();
        BillService billService = (BillService) servletContext.getAttribute(Constant.BILL_SERVICE_MANAGER);
        for (OrderDTO order : orderDTOList) {
            bills.addAll(billService.getBillsForOneOrder(order));
        }
        request.setAttribute("billList" , bills);
        request.setAttribute("orderDTOList", orderDTOList);
        return Path.PAGE_CLIENT_ORDER_LIST;
    }
}
