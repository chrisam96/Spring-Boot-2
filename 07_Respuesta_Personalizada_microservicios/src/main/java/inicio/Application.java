package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"controller", "service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*Se agrega para hacer uso del RestTemplate para
	 * el consumo de otro de Microservicios
	*/
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
}