package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"controller"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/* INTERCEPTORES
	 * 
	 * Podemos agregar las credenciales que el microservicio servidor requiere 
	 * para su autenticación a través de la clase BasicAuthenticationInterceptor. 
	 * 
	 * Una vez ingresado las credenciales en su constructor, se agrega al objeto 
	 * del RestTemplate, a través de los interceptores con el método 
	 * "getInterceptors().add()".

		Ejemplo:
		
		BasicAuthenticationInterceptor bai = 
			new BasicAuthenticationInterceptor("user","pass");
		RestTemplate temp = new RestTemplate();
		temp.getInterceptors().add(bai);
		
		NOTA:

		Los interceptores son objetos que realizan una tarea cuando se 
		produce una petición del RestTemplate.
	 */
	/*
	@Bean
	public RestTemplate template() {
		RestTemplate template = new RestTemplate();
		BasicAuthenticationInterceptor intercep;
		
		intercep = new BasicAuthenticationInterceptor("admin", "admin");
		template.getInterceptors().add(intercep);
		return template;
	}
	*/
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
}