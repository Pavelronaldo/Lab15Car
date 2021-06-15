package nnnocturn.web.command;

import nnnocturn.db.entity.User;
import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.util.Util;
import nnnocturn.web.bean.SettingBean;
import nnnocturn.web.service.UserService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class ChangeSettingsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ageParameter = request.getParameter("age");
        User user = (User) request.getSession().getAttribute("user");

        SettingBean settingBean = new SettingBean();
        settingBean.setId(user.getId());
        settingBean.setFirstName(firstName);
        settingBean.setLastName(lastName);
        settingBean.setAge(ageParameter);

        Validator validator = (Validator) Command.servletContext.getAttribute(Constant.VALIDATOR);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);
        Map<String, String> error = validator.validate(settingBean, locale);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        request.setAttribute("errors", error);

        if (error.isEmpty() && util.isNotReSubmitting(request)) {
            UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
            userService.updateUserInfo(settingBean);
        }
        return Path.COMMAND_SETTINGS;
    }
}
