package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CarImageGetDto;
import lk.riyapola.riyapola.dto.CarImgDTO;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.CarImg;
import lk.riyapola.riyapola.repo.CarImgRepo;
import lk.riyapola.riyapola.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarImgService {
    private final CarImgRepo carImgRepo;
    private final CarRepo carRepo;

    @Autowired
    public CarImgService(CarImgRepo carImgRepo, CarRepo carRepo) {
        this.carImgRepo = carImgRepo;
        this.carRepo = carRepo;
    }

    @Transactional
    public CarImageGetDto saveImage(CarImgDTO carImgDTO) throws URISyntaxException, IOException {
        System.out.println(carImgDTO.getCarId());

        // Construct upload directory path
        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Ensure directories are created
        }

        // Save the image file to the upload directory
        File destinationFile = new File(uploadDir.getAbsolutePath() + "/" + carImgDTO.getImages().getOriginalFilename());
        carImgDTO.getImages().transferTo(destinationFile);

        // Fetch the Car entity using carId
        Car car = carRepo.findById(carImgDTO.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid car ID"));

        // Create CarImg entity
        CarImg img = new CarImg("uploads/" + carImgDTO.getImages().getOriginalFilename(), car);

        // Save CarImg entity
        CarImg savedImg = carImgRepo.save(img);

        return new CarImageGetDto(savedImg);
    }


    public CarImageGetDto updateImage(Integer imgId,CarImgDTO carImgDTO) throws URISyntaxException, IOException {


        // Construct upload directory path
        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Ensure directories are created
        }

        // Save the image file to the upload directory
        File destinationFile = new File(uploadDir.getAbsolutePath() + "/" + carImgDTO.getImages().getOriginalFilename());
        carImgDTO.getImages().transferTo(destinationFile);

        // Fetch the Car entity using carId
        Car car = carRepo.findById(carImgDTO.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid car ID"));

        // Fetch the existing CarImg entity using imgId
        CarImg img = carImgRepo.findById(imgId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid image ID"));

        // update the CarImg entity
        img.setImages("uploads/" + carImgDTO.getImages().getOriginalFilename());
        img.setCar(car);

        // Save  the updated CarImg entity
        if(carImgRepo.existsById(imgId)){
            CarImg savedImg = carImgRepo.save(img);

            return new CarImageGetDto(savedImg);
        }
      return null;
    }
    public List<CarImageGetDto>getAllCarImages(){
        List <CarImg> all=carImgRepo.findAll();
        List<CarImageGetDto> list=new ArrayList<>();
        for(CarImg carImg:all){
            list.add(new CarImageGetDto(carImg));
        }
        return list;
    }

    public String deleteCarImage(Integer carId){
        if(carImgRepo.existsById(carId)){
            carImgRepo.deleteById(carId);
            return "Car Image Deleted";
        }
       return "No Car image found";

    }
    }
