package nnnocturn.web.command;

import nnnocturn.db.Role;
import nnnocturn.exception.AppException;
import nnnocturn.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static nnnocturn.db.Role.ADMIN;
import static nnnocturn.db.Role.MANAGER;

public class ChangeLocaleCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        Role role = (Role) request.getSession().getAttribute("userRole");
        String forward;
        if (ADMIN.equals(role)) {
            forward = Path.COMMAND_ADMIN_PANEL;
        } else if (MANAGER.equals(role)) {
            forward = Path.COMMAND_MANAGER_LIST_ORDERS;
        } else {
            forward = Path.COMMAND_CLIENT_CAR_LIST;
        }
        return forward;
    }
}