package nnnocturn.web.service.impl;

import nnnocturn.db.dto.CarDTO;
import nnnocturn.db.entity.Brand;
import nnnocturn.db.entity.Car;
import nnnocturn.db.entity.Category;
import nnnocturn.db.repository.BrandRepository;
import nnnocturn.db.repository.CarRepository;
import nnnocturn.db.repository.CategoryRepository;
import nnnocturn.web.bean.OperationCarBean;
import nnnocturn.web.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarRepository CAR_REPOSITORY;

    private final BrandRepository BRAND_REPOSITORY;

    private final CategoryRepository CATEGORY_REPOSITORY;

    public CarServiceImpl(CarRepository CAR_REPOSITORY, BrandRepository BRAND_REPOSITORY, CategoryRepository CATEGORY_REPOSITORY) {
        this.CAR_REPOSITORY = CAR_REPOSITORY;
        this.BRAND_REPOSITORY = BRAND_REPOSITORY;
        this.CATEGORY_REPOSITORY = CATEGORY_REPOSITORY;
    }

    /**
     * Create new car
     * @param operationCarBean new car
     */
    @Override
    public void createCar(OperationCarBean operationCarBean) {
        CAR_REPOSITORY.createCar(getEntity(operationCarBean));
    }

    /**
     * Update car
     *
     * @param operationCarBean car to be update
     */
    @Override
    public void updateCar(OperationCarBean operationCarBean) {
        CAR_REPOSITORY.updateCar(getEntity(operationCarBean));
    }

    /**
     * Delete car
     *
     * @param operationCarBean car to be remove
     */
    @Override
    public void deleteCar(OperationCarBean operationCarBean) {
        CAR_REPOSITORY.deleteCar(getEntity(operationCarBean));
    }

    /**
     * @return list all car DTO
     */
    @Override
    public List<CarDTO> getAllCarDTO() {
        return CAR_REPOSITORY.getAllCarDTO();
    }

    /**
     * @return list category
     */
    @Override
    public List<Category> getCategoryList() {
        return CATEGORY_REPOSITORY.getCategoryList();
    }

    /**
     * @return list brand
     */
    @Override
    public List<Brand> getBrandList() {
        return BRAND_REPOSITORY.getBrandList();
    }

    /**
     * Get entity car
     *
     * @param operationCarBean car
     * @return car object
     */
    private Car getEntity(OperationCarBean operationCarBean) {
        Car car = new Car();
        car.setId(operationCarBean.getId());
        car.setCost(operationCarBean.getCost());
        car.setModel(operationCarBean.getModel());
        car.setIdBrand(operationCarBean.getIdBrand());
        car.setIdCategory(operationCarBean.getIdCategory());
        return car;
    }
}
