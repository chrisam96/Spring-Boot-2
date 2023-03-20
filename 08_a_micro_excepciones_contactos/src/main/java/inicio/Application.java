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
