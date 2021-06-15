package nnnocturn.web.command;

import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.util.Util;
import nnnocturn.web.bean.UpdateStatusBean;
import nnnocturn.web.service.UserService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class UpdateUserStatusCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String idParameter = request.getParameter("id_user");
        String statusParameter =request.getParameter("status");

        int idStatus = Integer.parseInt(statusParameter);

        UpdateStatusBean statusBean = new UpdateStatusBean();
        statusBean.setId(idParameter);
        statusBean.setStatus(idStatus);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(statusBean,locale);
        request.setAttribute("errorsStatus", errors);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        if (errors.isEmpty()&&util.isNotReSubmitting(request)) {
            UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
            userService.updateUserStatus(statusBean);
        }
        return Path.COMMAND_ADMIN_PANEL;
    }
}


