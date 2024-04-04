package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImgDTO {
   private  int imgId;
   private MultipartFile image;
   private int car_id;
}
