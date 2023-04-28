package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"controller", "dao", "service"})
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages = {"dao"})
@SpringBootApplication
public class Application {

	/*
	 * @ComponentScan: Spring buscará en los paquetes indicados
	 * las anotaciones (de Stereotypes) e instanciará las clases
	 * contenidas
	 * 
	 * @EntityScan: Usado para que Spring reconozca en que paquete 
	 * estan las entidades
	 * 
	 * @EnableJpaRepositories: Indica en que paquete esta 
	 * implementada la interfaz de JPARepository
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/**[[[APUNTES DE EUREKA CLIENTE SERVIDOR]]]
 *  
 *  <<EURELA SERVER>>
 *  > Servidor para registro y descubrimiento de servicios
 *  > Permite acceder a los servicios sin conocer su ubicación real y puerto
 *  
 *  <<REGISTRO DE UN SERVICIO EN EUREKA>>
 *  > Requiere de la dependencia Eureka Discovery Client:
 *  
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
	
	E internamente se inyecta el Spring Security (Básico)
 *  
 *  > En el archivo de configuración, sea .yml o .properties, 
 *  se indica el nombre de registro del servicio y la url del servidor Eureka:
 *  
 *  spring.application.name=servicio-contactos
	server.port=9018
	eureka.client.service-url.defaultZone=http://localhost:8761/eureka
	     
	NOTAS:
	
	a< eureka.client.service-url: recibe valores tipo 
	"java.util.Map<java.lang.String,java.lang.String>" por lo que necesita
	valores en clave-valor
	
	b< eureka.client.service-url: es la dirección a la cual Eureka Server
	estará registrado para que los otros microservicios sepan donde deben
	registrase o descubrir otros microservicios  
 *  
 */