package nnnocturn.web.command;

import nnnocturn.db.dto.OrderDTO;
import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CheckOrderViewCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
        List<OrderDTO> orderDTOList = orderService.getConsideringOrdersDTO();
        request.setAttribute("orderDTOList", orderDTOList);
        String selectedOption = request.getParameter("decide");
        if (Objects.nonNull(selectedOption) && !selectedOption.isEmpty()) {
            request.setAttribute("selectedOption", selectedOption);
        }
        return Path.PAGE_MANAGER_ORDER_LIST;
    }
}