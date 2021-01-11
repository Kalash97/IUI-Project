package pl.kielce.tu.travel_agency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

    private String email;

    private String password;

    private Boolean rememberMe = false;
}
