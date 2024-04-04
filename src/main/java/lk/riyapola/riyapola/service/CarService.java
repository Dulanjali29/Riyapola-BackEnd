package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CarDTO;
import lk.riyapola.riyapola.dto.CarImgSavetDto;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.repo.CarRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }


    public CarImgSavetDto saveCar(CarDTO carDTO) throws URISyntaxException, IOException {

        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");

        uploadDir.mkdir();

        carDTO.getCarName().transferTo(new File(uploadDir.getAbsolutePath() + "/" + carDTO.getCarName().getOriginalFilename()));

        Car car =new Car(
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getNoOfPassengers(),
                carDTO.getFuelType(),
                carDTO.getTransmissionMode(),
                carDTO.getDailyRentalPrice(),
                carDTO.getStatus(),
                carDTO.getCarName().getOriginalFilename());

        car.setCarName("uploads/" +carDTO.getCarName().getOriginalFilename());

        Car carNew = carRepo.save(car);
        System.out.println(carNew);

        return null;
    }

    public List<Car> getAllCar(){
       List <Car>allCar=carRepo.findAll();
       return  allCar;
    }
//    public Car updateCar(Integer id, CarDTO carDTO) {
//
//        if (carRepo.existsById(id)) {
//            Car save=carRepo.save(new Car(
//                    id,
//                    carDTO.getBrand(),
//                    carDTO.getModel(),
//                    carDTO.getNoOfPassengers(),
//                    carDTO.getFuelType(),
//                    carDTO.getTransmissionMode(),
//                    carDTO.getDailyRentalPrice(),
//                    carDTO.getStatus()
//            ));
//
//            return  save;
//        }else {
//            return null;
//        }
//
//    }
    public String deleteCar(Integer id) {
        if (carRepo.existsById(id)) {
            carRepo.deleteById(id);
            return "Car Deleted!";
        }
        return "No Car Found!";
    }
}
