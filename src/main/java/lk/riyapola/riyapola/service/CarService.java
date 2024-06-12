package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CarDTO;
import lk.riyapola.riyapola.dto.CarImageGetDto;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.CarImg;
import lk.riyapola.riyapola.repo.CarRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car saveCar(CarDTO carDTO) {

        Car save = carRepo.save(new Car(

                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getNoOfPassengers(),
                carDTO.getFuelType(),
                carDTO.getTransmissionMode(),
                carDTO.getDailyRentalPrice(),
                carDTO.getStatus()

        ));
        System.out.println(save.getCarId());
        System.out.println(save);
        return save;

    }

    public List<Car> getAllCar(){
       List <Car>allCar=carRepo.findAll();
       return  allCar;
    }



    public Car updateCar(Integer carId, CarDTO carDTO)  {
    Car carsave=carRepo.findById(carId).orElse(null);

            if(carsave!=null){

                carsave.setBrand( carDTO.getBrand());
                carsave.setModel( carDTO.getModel());
                carsave.setNoOfPassengers( carDTO.getNoOfPassengers());
                carsave.setFuelType( carDTO.getFuelType());
                carsave.setTransmissionMode( carDTO.getTransmissionMode());
                carsave.setDailyRentalPrice( carDTO.getDailyRentalPrice());
                carsave.setStatus( carDTO.getStatus());

                Car saved=carRepo.save(carsave);

                return saved;

            }else{
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
