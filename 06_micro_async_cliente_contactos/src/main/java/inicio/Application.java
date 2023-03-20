package inicio;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@ComponentScan(basePackages = {"controller", "service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	/* @EnableAsync
	 * 
	 * >Anotación para habilitar la capacidad de ejecucíón
	 * asíncrona  
	 * 
	 * Executor y ThreadPoolTaskExecutor
	 * 
	 * > Spring ejecuta tareas en segundo plano a través 
	 * de la interfaz Executor. 
	 * 
	 * > Se creará una implementación de Executor en una 
	 * clase de configuración. @SpringBootApplication 
	 * ya contiene esa anotación incluida integrada
	 * 
	 * NOTA::
	 * ThreadPoolTaskExecutor es una implementación de
	 * la interfeaz Executor
	 */
	
	@Bean
	public Executor executor() {
		return new ThreadPoolTaskExecutor();
	}
	
}