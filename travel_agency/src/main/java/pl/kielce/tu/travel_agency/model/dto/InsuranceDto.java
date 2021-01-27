package pl.kielce.tu.travel_agency.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kielce.tu.travel_agency.model.entities.Insurance;

@Data
@NoArgsConstructor
public class InsuranceDto {
    private long id;

    private double value;

    public InsuranceDto(Insurance insurance) {
        this.id = insurance.getId();
        this.value = insurance.getValue();
    }
}
