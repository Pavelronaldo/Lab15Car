package nnnocturn.web.command;

import nnnocturn.db.dto.CarDTO;
import nnnocturn.db.dto.UserDTO;
import nnnocturn.db.entity.Brand;
import nnnocturn.db.entity.Category;
import nnnocturn.exception.AppException;
import nnnocturn.util.Constant;
import nnnocturn.util.Path;
import nnnocturn.web.service.CarService;
import nnnocturn.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ControlViewCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
        CarService carService = (CarService) servletContext.getAttribute(Constant.CAR_SERVICE_MANAGER);

        List<UserDTO> userDTOList = userService.getUserDTO();
        List<CarDTO> carDTOList = carService.getAllCarDTO();
        List<Brand> brandList = carService.getBrandList();
        List<Category> categoryList = carService.getCategoryList();
        request.setAttribute("userDTOList", userDTOList);
        request.setAttribute("carDTOList", carDTOList);
        request.setAttribute("brandList", brandList);
        request.setAttribute("categoryList", categoryList);
        userDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        carDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return Path.PAGE_ADMIN_CONTROL;
    }
}