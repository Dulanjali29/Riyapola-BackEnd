package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.dto.CarDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.repo.CarRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }


    public Car saveCar(CarDTO carDTO) {

        if (carDTO != null) {
            Car save = carRepo.save(new Car(
                    carDTO.getBrand(),
                    carDTO.getModel(),
                    carDTO.getNoOfPassengers(),
                    carDTO.getFuelType(),
                    carDTO.getDailyRentalPrice(),
                    carDTO.getStatus()

            ));
            return save;
        }

        return null;
    }
}
