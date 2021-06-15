package nnnocturn.web.command;

import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.util.Util;
import nnnocturn.web.bean.OperationCarBean;
import nnnocturn.web.service.CarService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class UpdateCarCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String idParameter = request.getParameter("id_car");
        String modelParameter = request.getParameter("model");
        String costParameter = request.getParameter("cost");
        String brandParameter = request.getParameter("brand");
        String categoryParameter = request.getParameter("category");

        OperationCarBean operationCarBean = new OperationCarBean();
        operationCarBean.setId(idParameter);
        operationCarBean.setCost(costParameter);
        operationCarBean.setModel(modelParameter);
        operationCarBean.setIdBrand(brandParameter);
        operationCarBean.setIdCategory(categoryParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(operationCarBean, locale);
        request.setAttribute("errorsUpdate", errors);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            CarService carService = (CarService) servletContext.getAttribute(Constant.CAR_SERVICE_MANAGER);
            carService.updateCar(operationCarBean);
        }
        return Path.COMMAND_ADMIN_PANEL;
    }
}