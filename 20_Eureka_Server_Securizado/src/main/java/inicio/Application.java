package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/**[[[APUNTES DE EUREKA SERVER - SECURIZADO]]]
 *  
 *  <<EURELA SERVER>>
 *  > Servidor para registro y descubrimiento de servicios
 *  > Permite acceder a los servicios sin conocer su ubicación real y puerto
 *  
 *  <<CREACION DEL SERVIDOR EUREKA>>
 *  > Se crea como cualquier microservicio Spring Boot 
 *  > Requiere de la dependencia Eureka Server:
 *  
 *  <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
	</dependency>
	
	E internamente se inyecta el Spring Security (Básico)
 *  
 *  > En la clase main, se debe anotar con @EnableEurekaServer
 *  sobre la etiqueta de @SpringBootApplication
 *  
 *  @EnableEurekaServer
	@SpringBootApplication
	public class Application {

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	}
 *  
 *  > En al aplicacion, sea .yml o .properties, se deben agregar las
 *  siguientes propiedades
 *  
 *  spring:
  		application:
    		name: eureka-server
  		security:
		    user:
		      name: user
		      password: pass
	server:
	  port: 8761
	eureka:
	  client:
	    register-with-eureka: false
	    service-url:
	      defaultZone: http://localhost:8761/eureka
	     
	NOTAS:
	a< eureka.client.register-with-eureka: false, sirve para que un 
	microservicio no se vaya a registrar ante Eureka
	
	b< eureka.client.service-url: recibe valores tipo 
	"java.util.Map<java.lang.String,java.lang.String>" por lo que necesita
	valores en clave-valor
	
	c< eureka.client.service-url: es la dirección a la cual Eureka Server
	estará registrado para que los otros microservicios sepan donde deben
	registrase o descubrir otros microservicios  
 *  
 */

