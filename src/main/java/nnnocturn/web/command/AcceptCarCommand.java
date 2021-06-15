package nnnocturn.web.command;

import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.util.Util;
import nnnocturn.web.bean.AcceptCarBean;
import nnnocturn.web.service.BillService;
import nnnocturn.web.service.OrderService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class AcceptCarCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String decideParameter = request.getParameter("decide");
        String commentParameter = request.getParameter("penaltyComment");
        String idParameter = request.getParameter("orderId");
        String costParameter = request.getParameter("cost");

        AcceptCarBean acceptCarBean = new AcceptCarBean();
        acceptCarBean.setId(idParameter);
        acceptCarBean.setDecide(decideParameter);
        acceptCarBean.setComment(commentParameter);
        acceptCarBean.setCost(costParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);
        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(acceptCarBean,locale);
        request.setAttribute("errors", errors);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
            orderService.setClosedStatus(acceptCarBean);
            if ("penalty".equals(decideParameter)) {
                BillService billService = (BillService) servletContext.getAttribute(Constant.BILL_SERVICE_MANAGER);
                billService.createBill(acceptCarBean);
            }
        }
        return Path.COMMAND_MANAGER_CHECK_CARS;
    }
}
