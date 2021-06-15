package nnnocturn.web.command;

import org.apache.log4j.Logger;
import nnnocturn.db.Role;
import nnnocturn.db.UserStatus;
import nnnocturn.db.entity.User;
import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.web.bean.AuthBean;
import nnnocturn.web.service.UserService;
import nnnocturn.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LogInCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LogInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        String password = request.getParameter("password");

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);

        AuthBean authBean = new AuthBean();
        authBean.setLogin(login);
        authBean.setPassword(password);

        User user = userService.findUser(authBean);
        authBean.setUser(user);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(authBean);
        request.setAttribute("errors", errors);
        String forward = Path.PAGE_LOGIN;
        if (errors.isEmpty()) {
            Role userRole = Role.getRole(user);
            LOG.trace("userRole --> " + userRole);
            UserStatus userStatus = UserStatus.getStatus(user);
            LOG.trace("userStatus --> " + userStatus);

            if (userRole == Role.MANAGER) {
                forward = Path.COMMAND_MANAGER_LIST_ORDERS;
            }
            if (userRole == Role.CLIENT) {
                forward = Path.COMMAND_CLIENT_CAR_LIST;
            }
            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND_ADMIN_PANEL;
            }

            if(userStatus.equals(UserStatus.BUNNED)){
                request.setAttribute("errorMessage","You was banned");
                forward = Path.PAGE_ERROR_PAGE;
            }else {
                session.setAttribute(Constant.USER_STATUS, userStatus);
                session.setAttribute(Constant.USER, user);
                LOG.trace("Set the session attribute: user --> " + user);
                session.setAttribute(Constant.USER_ROLE, userRole);
            }
            LOG.trace("Set the session attribute: userRole --> " + userRole);
            LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
            LOG.debug("Command finished");
        } else {
            request.setAttribute("login", login);
        }
        return forward;
    }
}
