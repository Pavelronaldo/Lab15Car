package nnnocturn.web;

import nnnocturn.db.UserStatus;
import nnnocturn.db.entity.User;
import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.web.command.Command;
import nnnocturn.web.command.CommandContainer;
import nnnocturn.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class Controller extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Command command = getCommand(request);

        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }

        String language = request.getParameter("language");
        request.setAttribute("selectedLanguage", language);
        request.getRequestDispatcher(forward).forward(request, response);
    }

    /**
     * Get command from request
     * @param request get from form
     * @return command
     */
    private Command getCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        UserService userService = (UserService) getServletContext().getAttribute(Constant.USER_SERVICE_MANAGER);
        User user = (User) request.getSession().getAttribute(Constant.USER);
        if (Objects.nonNull(user)) {
            user = userService.findUser(user);
            request.getSession().setAttribute(Constant.USER, user);
            if (UserStatus.BUNNED.getNumber() == user.getIdStatus()) {
                command = CommandContainer.getCommand("logout");
            }
        }
        return command;
    }
}
