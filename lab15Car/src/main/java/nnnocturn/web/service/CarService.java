package nnnocturn.web.service;

import nnnocturn.db.dto.CarDTO;
import nnnocturn.db.entity.Brand;
import nnnocturn.db.entity.Category;
import nnnocturn.web.bean.OperationCarBean;

import java.util.List;

public interface CarService {

    void createCar(OperationCarBean operationCarBean);

    void updateCar(OperationCarBean operationCarBean);

    void deleteCar(OperationCarBean operationCarBean);

    List<CarDTO> getAllCarDTO();

    List<Category> getCategoryList();

    List<Brand> getBrandList();
}
