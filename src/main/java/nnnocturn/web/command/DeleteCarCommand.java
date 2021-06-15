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

public class DeleteCarCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String idParameter = request.getParameter("id_car");

        OperationCarBean operationCarBean = new OperationCarBean();
        operationCarBean.setId(idParameter);
        operationCarBean.setCost(Constant.NOT_EMPTY_NUMBER_FIELD);
        operationCarBean.setModel(Constant.NOT_EMPTY_STRING_FIELD);
        operationCarBean.setIdBrand(Constant.NOT_EMPTY_NUMBER_FIELD);
        operationCarBean.setIdCategory(Constant.NOT_EMPTY_NUMBER_FIELD);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);
        Map<String, String> errors = validator.validate(operationCarBean,locale);
        request.setAttribute("errorsDelete", errors);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            CarService carService = (CarService) servletContext.getAttribute(Constant.CAR_SERVICE_MANAGER);
            carService.deleteCar(operationCarBean);
        }
        return Path.COMMAND_ADMIN_PANEL;
    }
}
