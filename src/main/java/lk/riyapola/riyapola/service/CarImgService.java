package lk.riyapola.riyapola.service;

import lk.riyapola.riyapola.dto.CarImageGetDto;
import lk.riyapola.riyapola.dto.CarImgDTO;
import lk.riyapola.riyapola.entity.Car;
import lk.riyapola.riyapola.entity.CarImg;
import lk.riyapola.riyapola.repo.CarImgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.service
 * Date : 5/28/2024
 * Time :4:18 PM
 */

@Service
public class CarImgService {
    private  final CarImgRepo carImgRepo;
@Autowired
    public CarImgService(CarImgRepo carImgRepo) {

    this.carImgRepo = carImgRepo;
    }
    public CarImageGetDto saveImage(CarImgDTO carImgDTO) throws URISyntaxException, IOException {
        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");
        uploadDir.mkdir();
        carImgDTO.getImages().transferTo(new File(uploadDir.getAbsolutePath() + "/" + carImgDTO.getImages().getOriginalFilename()));

        CarImg img=new CarImg(carImgDTO.getImages().getOriginalFilename(),carImgDTO.getCarId(),carImgDTO.getImgId());
        img.setImages("uploads/"+carImgDTO.getImages().getOriginalFilename());


        System.out.println("car id 2-"+carImgDTO.getCarId());

        CarImg saved=carImgRepo.save(img);

        System.out.println(saved);
        return new CarImageGetDto(saved);
    }
}
