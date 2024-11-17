package api.portfolio.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactDTO {
    private Long id;
    private String phone;
    private String email;
    private String address;

}