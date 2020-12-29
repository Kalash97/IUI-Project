package pl.kielce.tu.travel_agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.kielce.tu.travel_agency.config.ServerConfig;

@SpringBootApplication
@EnableConfigurationProperties({ServerConfig.class})
public class TravelAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

}
