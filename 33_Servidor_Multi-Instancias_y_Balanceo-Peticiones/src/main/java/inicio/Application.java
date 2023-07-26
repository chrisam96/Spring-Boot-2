package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "controller")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/* [[[MULTIPLES INSTANCIAS Y BALANCEO DE PETICIONES]]]
 * 
 * >>>Balanceado de peticiones<<<
 * 
 * > La librería Ribbon (parte integral de Eureka-Netflix) 
 * es capaz de balancear peticiones 
 * a un microservicio entre varias instancias del mismo
 * 
 * > Esto permite aplicar políticas de alta disponibilidad
 * y mejora de rendimiento
 * 
 * > Por defecto, se realiza balanceo de tipo round-ribbon.
 * Es decir, las peticiones son repartidas entre el número 
 * de instancias (de forma ciclica).
 * 
 * En un ejemplo, podemos imaginar 3 instancias de un microservicio 
 * y 5 peticiones, en donde Ribbon repartirá 
 * la primera peticion a la primera instancia, 
 * la segunda peticion a la segunda instancia,
 * la tercera peticion a la tercera instancia y en el caso de
 * la cuarta peticion irá de nuevo a la primera instancia
 * la quinta peticion irá de nuevo a la segunda instancia   
 * 
 * >>> PROPIEDADES DE CONFIGURACION PARA LA INSTANCIA <<<
 * 
 * > Cada instancia del servicio deberá tener un valor único para la propiedad 
 * "eureka.instance.instance-id":
		eureka.instance.instanceld=instanciaA
 *
 *> O con un valor aleatorio:
	eureka.instance.instanceld=${random.value]:${spring.application.name}
 * 
 * >>>GENERACION DE MULTIPLES INSTANCIAS<<<
 * 
 * > Todas las instancias se generan desde la misma aplicación Spring Boot
 * 
 * > Para generar una nueva instancia se deben cambiar dos propiedades
 * en el archivo "application":
 * 	- spring.application.name
 * 	- server.port
 * 
 * Además de levantar de nuevo (desplegar/deployar) de nuevo el micro
 * 
 * >>> REGISTRO EN EUREKA <<<
 * 
 * > Al lanzar cada instancia del mismo servicio, aparecerán registradas  
 * en el servidor de Eureka con numeor entre parentesis que indica
 * las instancias a dicho microservicio.
 * 
 * >>> @LoadBalanced Y BALANCEO DE PETICIONES <<<
 * 
 * > @LoadBalanced
 * Activa las librerias Ribbon para ejecutar el
 * balanceo de cargas (Balanceo de peticiones)
 * 
 * > Para generar el balanceo de las peticiones estas se deben pedir
 * del lado del cliente, empleando la anotacion @LoadBalanced sobre
 * el @Bean que se usará para las peticiones HTTP
 * 
 * Ej:
 	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
 * */
