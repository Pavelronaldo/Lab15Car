package nnnocturn.web.command;

import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.util.Util;
import nnnocturn.web.bean.PayOrderBean;
import nnnocturn.web.service.BillService;
import nnnocturn.web.service.OrderService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class PayOrderCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String payParameter = request.getParameter("pay");
        String billParameter = request.getParameter("bill");
        String statusParameter = request.getParameter("status");

        Validator validator = (Validator) servletContext.getAttribute("validator");
        Util util = (Util) servletContext.getAttribute("util");

        PayOrderBean payOrderBean = new PayOrderBean();
        payOrderBean.setIdOrder(payParameter);
        payOrderBean.setBill(billParameter);
        payOrderBean.setStatus(statusParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Map<String, String> errors = validator.validate(payOrderBean, locale);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            BillService billService = (BillService) servletContext.getAttribute(Constant.BILL_SERVICE_MANAGER);
            billService.payForBill(payOrderBean);
            if (!"closed".equals(statusParameter)) {
                OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
                orderService.setPaidStatus(payOrderBean);
            }
        }
        return Path.COMMAND_CLIENT_ORDER_LIST;
    }
}
