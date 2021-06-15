package nnnocturn.web.command;

import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.util.Util;
import nnnocturn.web.bean.RegistrationBean;
import nnnocturn.web.service.UserService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class RegistrationCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        String loginParameter = request.getParameter("login");
        String passwordParameter = request.getParameter("password");
        String confirmParameter = request.getParameter("confirm");
        String firstNameParameter = request.getParameter("firstname");
        String lastNameParameter = request.getParameter("lastname");
        String ageParameter = request.getParameter("age");

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);

        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setLogin(loginParameter);
        registrationBean.setPassword(passwordParameter);
        registrationBean.setConfirm(confirmParameter);
        registrationBean.setFirstName(firstNameParameter);
        registrationBean.setLastName(lastNameParameter);
        registrationBean.setAge(ageParameter);
        registrationBean.setUser(userService.findUser(registrationBean));
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(registrationBean,locale);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);

        String forward;
        if (errors.isEmpty() && util.isNotReSubmitting(request)) {

            userService.createClient(registrationBean);
            forward = Path.COMMAND_LOGIN;
        } else {
            request.setAttribute("login", loginParameter);
            request.setAttribute("firstname", firstNameParameter);
            request.setAttribute("lastname", lastNameParameter);
            request.setAttribute("age", ageParameter);
            request.setAttribute("errors", errors);
            forward = Path.PAGE_REGISTRATION;
        }
        return forward;
    }
}
