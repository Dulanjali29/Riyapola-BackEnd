package lk.riyapola.riyapola.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.riyapola.riyapola.dto.CarImageGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarImg  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer imgId;
  private String images;

  @ManyToOne
  @JoinColumn(name="car_Id")
  @JsonIgnore
  private   Car car;


  public CarImg(String originalFilename, int carId, int imgId) {
  }
}
