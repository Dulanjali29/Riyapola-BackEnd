package lk.riyapola.riyapola.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDTO {

    private  Integer admin_id;
    private String firstName;
    private  String lastName;
    private  String userName;
    private  String password;
    private  String role;
}
