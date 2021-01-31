package pl.kielce.tu.travel_agency.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class JWTToken {

    private String idToken;

    @Getter
    private PersonDto person;

    public JWTToken(String idToken, PersonDto person) {
        this.idToken = idToken;
        this.person = person;
    }

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}