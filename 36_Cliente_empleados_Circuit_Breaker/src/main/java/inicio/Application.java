package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"model", "service", "controller", "inicio"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
}

/* CIRCUIT BREAKER
 * Utilizado en contextos de recuperación ante fallos. 
 * En interacción con microservicios, permite que el
 * cliente pueda obtener respuesta ante fallos del servicio llamado.
 * 
 * [[[ Ejemplo de Funcionamiento ]]]
 * Dado 3 servicios, un cliente, un intermediario (Circuit Breaker) y un
 * servicio (servidor),en donde hay constante comunicación del cliente 
 * pasando por el intermediario para llegar al servicio y de reversa.
 * 
 * Se le llama CIRCUITO CERRADO cuando las peticiones del cliente 
 * son atendidas y son respondidas de vuelta desde el servicio|servidor
 *  estan OK, 
 *  
 * Se le llama CIRCUITO ABIERTO cuando las peticiones del cliente 
 * son fallidas ya que no hay respuesta del servicio|servidor, por lo que,
 * Circuit Breaker suple esa funcion de respuesta del servicio|servidor 
 * 
 * [[[ Estados de un Circuit Breaker ]]]
 * 
 * Un circuit breaker puede encontrarse en tres estados:
 * 
 * ~ Cerrado: El servicio atiende correctamente las peticiones del cliente. 
 * Circuito cerrado entre cliente y servicio.
 * 
 * ~ Abierto: El servicio no se encuentra disponible o genera un error. 
 * Se abre el circuito y el servicio se aísla del cliente, que es atendido 
 * por el propio circuit breaker
 * 
 * ~ Medio abierto: Tras pasar un tiempo en estado abierto, se intenta de 
 * nuevo una conexión con el servicio. Si está disponible, se pasa a cerrado, 
 * sino, se vuelve a abierto
 * 
 *  [[[ Implementaciones de Circuit Breaker ]]]
 *  
 *  En una arquitectura basada en microservicios, el patrón circuit breaker 
 *  se puede implementar mediante:
 *  
 *  • Hystrix: Librería de Netflix. Desaconsejada en últimas versiones de 
 *  Spring Cloud por acoplamiento entre el patrón y librería
 *  
 *  • Spring Cloud Circuit Breaker: Proporciona capa de abstracción, 
 *  válida para diferentes implementaciones. La implementación más utilizada 
 *  para esta especificación es Resilience4J
 *  
 *  [[[ Tipos de Circuit Breaker ]]]
 *  
 *  En función del criterio utilizado para cambiar de estado, un circuit 
 *  breaker puede ser:
 *  
 *  • Basado en contador: El circuit breaker cambia de estado cerrado a 
 *  abierto si las N ultimas llamadas al servicio han fallado
 *  
 *  • Basado en tiempo: El cambio de estado de cerrado a abierto se produce 
 *  si las llamadas realizadas en los últimos N segundos han fallado
 *  
 *  [[[ Dependencias de Resilience4J ]]]
 *
 *  Para aplicar la implementacion Resilience4J de Circuit Breaker a una aplicacion
 *  Spring Boot cliente de un microservicio, se debe añadir:
 *  
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
	</dependency>
	
 * [[[ API Circuit Breaker ]]]
 * 
 * • CircuitBreakerFactory: Permite crear un circuit breaker basado en una determinada 
 * configuración
 * 
 * • CircuitBreaker: Ejecuta la acción de llamada al servicio externo a través de un 
 * Supplier. Su método principal es run0:
 * 
 * <T> T run (Supplier <T> supplier, Function <Throwable> fallback)
 * 
 * 	Donde:
 * 	- <T> T :: Tipo (clase) de la respuesta
 *  - Supplier :: Código de llamada al servicio externo (servidor)
 *  - Function :: Implementa acciones en caso de fallas
 *  
 *  Tanto Supplier y Function son interfaces funcionales, es decir, 
 *  dentro de ellas se implementan Funciones LAMBDA
 *  
 *  NOTAS:
 *  I. Function devuelve un resultado en caso de que la llamada al servicio remoto falle. 
 *  ii. En la llamada de "Function" se le proporciona un parametro de tipo "Throwable".
 *  
 *  [[[ Metodos de Configuracion ]]]
 *  
 *  La librería Resilience4] proporciona una configuración por defecto para Spring Boot.
 *  Para configuración personalizada, se puede crear un CircuitBreakerConfig con los 
 *  siguientes parámetros:
 *  
 *  - slidingWindowType: Tipo de circuit breaker (COUNT_BASED o TIME_BASED), 
 *  por defecto COUNT_BASED.
 *  
 *  - slidingWindowSize: Tamaño del rango a estudiar, es decir, 
 *  número de últimos intentos o segundos a contabilizar.
 *  
 *  - failureRateThreshold: Porcentaje de últimos intentos o segundos que determinan 
 *  el UMBRAL DE FALLO.
 *  
 *  - failureRateThreshold: El umbral de tiempo que durará el Circuito MEDIO ABIERTO
 * */
