package develhope.DClinic;

import develhope.DClinic.security.auth.AuthenticationService;
import develhope.DClinic.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static develhope.DClinic.security.user.Role.ADMIN;
import static develhope.DClinic.security.user.Role.MANAGER;


@SpringBootApplication

public class DClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.fiscalCode("Admin")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.fiscalCode("Manager")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
