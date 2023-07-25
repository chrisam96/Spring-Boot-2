package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@ComponentScan(basePackages = {"controller", "dao", "service","inicio"})
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
/* [[[¿QUE ES SWAGGER?]]]
 * > Herramienta que proporciona una forma estandarizada de documentar 
 * un API REST.
 * > La documentación se genera de forma automática, a partir de 
 * anotaciones incluidas en el controller.
 * 
 * [[[DEPENDENCIAS DE SWAGGER]]]
 * Para utilizar swagger se deberán añadir las siguientes dependencias:

	<dependency>
		<groupid>io.springfox</groupld>
		<artifactid>springfox-swagger2</artifactid>
		<version>2.8.0</version>
	</dependency>
	<dependency>
		<groupid>io.springfox</groupld>
		<artifactid>springfox-swagger-ui</artifactid>
		<version>2.8.0</version>
	</dependency>
	
 *[[[CONFIGURACION]]}
 * > En una clase de configuración, se indicará el paquete o paquetes en 
 * los que se encuentra el controller y las URLs a documentar.
 * 
 * > Dentro del método "basePackage()" se inidica el PAQUETE donde estan 
 * el o las clases DEL CONTROLLER.
 * 
 * > En el método de "paths()", instanciando dentro el método de clase 
 * PathSelectors.regex(""), de tal manera que quedé como
 * "paths(PathSelectors.regex("/.*"))" es donde se indican 
 * que URLs (del servicio) se van A DOCUMENTAR
 * 
 * [[[ANOTACIONES DEL CONTROLADOR]]]
 * Se deberán incluir una serie de anotaciones en el controller para 
 * indicar la información que debe aparecer en la ayuda:
 * 
 * > @Api: Se coloca encima de la palabra "class" de la clase controlador 
 * para indicar, a través de suatributo "value", la información que debe 
 * aparecer en la pagina de ayuda sobre el controller.
 * 
 * > @ApiOperation: Utilizada en cada operacion (metodo) expuesto por el 
 * controller, para indicar mediante su atributo "value" el texto de ayuda
 * sobre la operación.
 * 
 * > @ApiParam: Se utiliza antea de cada parámetro de un método controlador
 * para informar sobre el uso del mismo.
 * 
 * [[[PAGINA DE ACCESO A LA AYUDA]]]
 * > Al compilar y ejecutar el servicio, se podrá acceder a la ayuda 
 * del mismo a través de la dirección:
 * 
 * http://[DIRECCION_IP]:[PUERTO]/swagger-ui.html
 */