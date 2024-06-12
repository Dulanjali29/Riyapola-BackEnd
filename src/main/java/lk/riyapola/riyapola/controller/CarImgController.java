package lk.riyapola.riyapola.controller;

import lk.riyapola.riyapola.dto.CarImageGetDto;
import lk.riyapola.riyapola.dto.CarImgDTO;
import lk.riyapola.riyapola.service.CarImgService;
import lk.riyapola.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/riyapola/image")
public class CarImgController {
    private final CarImgService carImgService;
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CarImgController(CarImgService carImgService, JWTTokenGenerator jwtTokenGenerator) {
        this.carImgService = carImgService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/addImage")
    public ResponseEntity<Object> saveImage(@ModelAttribute CarImgDTO carImgDTO, @RequestHeader(name = "Authorization") String authorizationHeader) throws URISyntaxException, IOException {
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            System.out.println(carImgDTO.getCarId());
            CarImageGetDto carImageGetDto = carImgService.saveImage(carImgDTO);
            return new ResponseEntity<>(carImageGetDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/updateImage/{imgId}")
    public ResponseEntity<Object> updateImage(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer imgId,@ModelAttribute CarImgDTO carImgDTO)throws URISyntaxException,IOException{
        if (jwtTokenGenerator.validateJwtToken(authorizationHeader)) {

            CarImageGetDto carImageGetDto = carImgService.updateImage(imgId,carImgDTO);
            if(carImageGetDto!=null){
                return new ResponseEntity<>(carImageGetDto, HttpStatus.OK);
            }
            return new ResponseEntity<>("Image not found or could not be updated", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/getAllCarImages")
    public ResponseEntity<Object> getAllCarImages(){
        List<CarImageGetDto> getDto=carImgService.getAllCarImages();
        return  new ResponseEntity<>(getDto,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCarImage/{carId}")
    public ResponseEntity<String>deleteCarImage(@RequestHeader( name = "Authorization") String authorizationHeader,@PathVariable Integer carId)throws IOException,URISyntaxException{
    if(jwtTokenGenerator.validateJwtToken(authorizationHeader)){
        String output=carImgService.deleteCarImage(carId);
       return new ResponseEntity<>(output,HttpStatus.OK);
    }else {
        return new ResponseEntity<>("Invalid token By Admin", HttpStatus.FORBIDDEN);
     }
    }
}
