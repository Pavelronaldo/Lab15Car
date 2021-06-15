package nnnocturn.db.repository;

import nnnocturn.db.dto.CarDTO;
import nnnocturn.db.entity.Car;

import java.util.List;

public interface CarRepository {

    void createCar(Car car);

    void updateCar(Car car);

    void deleteCar(Car car);

    List<CarDTO>getAllCarDTO();
}
