package api.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "api.portfolio")
public class PortfolioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}

}