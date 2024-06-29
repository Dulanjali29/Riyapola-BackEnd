package lk.riyapola.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dulanjali
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.dto
 * Date : 6/21/2024
 * Time :12:22 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDetailsDTO {
    private  String toMail;
    private String message;
    private String subject;

}
