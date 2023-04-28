package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*[[[Cliente de Open Feign]]]
 * 
 * Cliente de un servicio REST cuya implementación es proporcionada por Spring. 
 * Actúa de proxy entre el servicio REST y la aplicación cliente real. 
 * Es compatible con Eureka Server.
 * 
 * 
 * {{{Dependencias y configuración de OpenFeign]]]
 * 
 * > En la aplicación o microservicio cliente añadiremos el siguiente starter:

	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-openfeign</artifactId>
	</dependency>
	
	> Se añadirá la anotación @EnableFeignClients en la clase lanzadora-
 */

/* [[[@EnableFeignClients]]]
 * 
 * > Habilita el uso de OpenFeign. 
 * 
 * > No se define algún metodo RestTemplate anotado con @Bean,
 * porque ya se esta usando PersonaFeign que a su vez emplea OpenFeign. 
 * OpenFeign implicitamente crea un RestTemplate al indicarse en el atributo value
 * que identifique algún servicio (que tiene que descubrirlo a tráves de Eureka).
 * 
 */

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"controller","inicio"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

