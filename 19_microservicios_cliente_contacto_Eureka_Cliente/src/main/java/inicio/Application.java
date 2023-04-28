package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"controller"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
}
/*[[[DESCUBRIMIENTO DE SERVICIOS]]]

	>Al igual que el servicio registrado, el servicio cliente requiere la dependencia Eureka Client
	
	<dependency>
	    <groupId>org.springframework.cloud</groupId>
	    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
	
	>El método de creación RestTemaplate anotado con @LoadBalanced para activar libreria Ribbon:
	
	@LoadBalanced
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	La biblioteca Ribbon la utilizan las aplicaciones clientes para acceder a los microservicios 
	(registrados) a tráves de Eureka. Permite acceder a la instancía de un microservicio sin necesidad 
	de conocer la dirección concreta de un micro. Ribbon además de proporcionar balanceo de carga. 
	
	Ribbon, utilizando el identificador de servicio servidor (URL) accedería a la tabla de 
	registro de Eureka para localizar la dirección real del servicio.
	
	>Acceso mediante el nombre de registro:
	
	String UPL_SERVICE = "http://servicio-ciudades"
 * */


