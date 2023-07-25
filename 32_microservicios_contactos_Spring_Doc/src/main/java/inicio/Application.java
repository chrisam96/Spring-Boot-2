package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@ComponentScan(basePackages = {"controller", "dao", "service"})
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages = {"dao"})
@SpringBootApplication
@RequestMapping("/")
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
/* [[[SPRING DOC]]}
 * > Librería Spring para la documentación de servicios REST bajo el
 * estándar Open API.
 * 
 * ›Open API: Estándar abierto para la documentación de servicios
 * REST. Admite diferentes formatos, entre ellos swagger.
 * 
 * ?Aunque se puede utilizar con versiones anteriores de Spring
 * Boot, desde Spring Boot 3.x es la única librería compatible para
 * la documentación de servicios REST.
 * 
 * [[[DEPENDENCIAS SPRING DOC]]]
 * Para documentar servicios con Spring doc a partir de Spring 
 * Boot 3.x, se emplean la siguientes dependencias:
 * 
	<dependency>
	      <groupId>org.springdoc</groupId>
	      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	      <version>2.0.0</version>
	</dependency>
	<dependency>
	       <groupId>org.springframework.boot</groupId>
	       <artifactId>spring-boot-starter-validation</artifactId>
	 </dependency>

 *
 * NOTA: 
 * Se debe agregar manualmente porque el asistente de Eclipse 
 * no lo agrega automaticamente
 * 
 * La segunda dependencia es para la compatibilidad 
 * con Spring Boot 3, ya que esta dependencia se basa en Java EE 8
 * 
 * [[[CONFIGURACION]]]
 * 
 * > No es necesario definir una clase de configuración para generar una 
 * documentación estilo swagger.
 * 
 * > Basta con definir las siguientes propiedades en el archivo 
 * application.properties:
 * 
 * 		springdoc.packagesToScan=controller
 * 		springdoc.pathsToMatch=/**
 * 
 * NOTA: Sobre las propiedades:
 * I. El Paquete donde se quiere escanear los Controller
 * II. Las direcciones URLs que se quieren documentar
 * 
 * > No es necesario definir la propiedad matching-strategy
 * 
 * 
[[[ANOTACIONES DEL CONTROLADOR]]]

Spring doc soporta las siguientes anotaciones para la definición
de la clase controladora:

> @Tag: Se coloca delante de la clase controlador para agrupar las operaciones bajo 
una o varias etiquetas (tags). Dichas etiquetas se indican en el atributo "tags".

> @Operation: Utilizada en cada operación expuesta por el controller. El texto 
inicial de ayuda se indica a través de "summary", mientras que en "description" se 
ofrece información más detallada.

> @Parameter: Se utiliza delante de cada parámetro del método controlador para 
informar sobre el uso del mismo. Mediante el atributo description se especifica 
una descripción del parámetro

[[[ACCESO A LA AYUDA]]]

> Al compilar y ejecutar el servicio, se podrá acceder a la ayuda del mismo 
a través de la dirección:

	http://[dir_ip]:[puerto]/context-path/swagger-ui.html
 * */
