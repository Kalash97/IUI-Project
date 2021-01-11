package pl.kielce.tu.travel_agency.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "ta")
@Data
@Validated
public class ServerConfig {

    /** Default first-launch email address*/
    //@Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String default_login = "user@localhost";

    /** Default first-launch password*/
    private String default_password;

    /** Base64 encoded token signing key. If not defined, generated automatically every launch. */
    private String secret_key;
}