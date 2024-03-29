package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.AdminDTO;
import lk.riyapola.riyapola.dto.CarDTO;
import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.repo.CarRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    carDTO.getTransmissionMode(),
                    carDTO.getDailyRentalPrice(),
                    carDTO.getStatus()

            ));
            return save;
        }

        return null;
    }
    public List<Car> getAllCar(){
       List <Car>allCar=carRepo.findAll();
       return  allCar;
    }
    public Car updateCar(Integer id, CarDTO carDTO) {

        if (carRepo.existsById(id)) {
            Car save=carRepo.save(new Car(
                    id,
                    carDTO.getBrand(),
                    carDTO.getModel(),
                    carDTO.getNoOfPassengers(),
                    carDTO.getFuelType(),
                    carDTO.getTransmissionMode(),
                    carDTO.getDailyRentalPrice(),
                    carDTO.getStatus()
            ));

            return  save;
        }else {
            return null;
        }

    }
    public String deleteCar(Integer id) {
        if (carRepo.existsById(id)) {
            carRepo.deleteById(id);
            return "Car Deleted!";
        }
        return "No Car Found!";
    }
}
