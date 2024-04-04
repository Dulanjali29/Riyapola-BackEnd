package lk.riyapola.riyapola.dto;

import lk.riyapola.riyapola.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarImgSavetDto {
private  int imgId;
private String image;
private Car car_Id;

}
