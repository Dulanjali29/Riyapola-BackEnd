package lk.riyapola.riyapola.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarImg {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer imgId;
  private String image;

  @ManyToOne
  @JoinColumn(name="car_id")
  @JsonIgnore
   private  Car car;

}
