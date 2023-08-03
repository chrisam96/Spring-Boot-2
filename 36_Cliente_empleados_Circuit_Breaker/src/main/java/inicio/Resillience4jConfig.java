package inicio;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;

//Clase configuradora de Circuit Breaker
@Configuration
public class Resillience4jConfig {

	/* Mas INFO sobre metodos de Configuracion: 
	 * https://resilience4j.readme.io/docs/circuitbreaker
	 * */
	
	/* Creacion del CircuitBreakerConfig (que es un tipo factory)
	 * que siempre se generara de forma global,
	 * es decir, se crea de manera universal y por defecto, 
	 * al menos que se especifique otro factory mediante su ID
	 * (pasando como parametro el ID del CircuitBreakerConfig especifico)
	 */
	CircuitBreakerConfig config =  CircuitBreakerConfig.custom()
		//Tipo de Circuit Breaker:: COUNT_BASED || TIME_BASED
		.slidingWindowType(SlidingWindowType.COUNT_BASED)
		//Numero de ultimos intentos o segundos a considerar (contabilizar)
		.slidingWindowSize(6)
		//Umbral de fallo:: Porcentaje de fallo || Ultimos segundos
		.failureRateThreshold(50)
		/* El umbral de tiempo que durará el Circuito MEDIO ABIERTO
		 * 
		 * Una vez que el circuito sea abierto, este metodo indicará
		 * cuanto tiempo CB esperará para tratar
		 * de retornar a circuito cerrado
		*/
		.waitDurationInOpenState(Duration.ofMillis(40000))
		//Construye el objeto a partir de los metodos de configuracion
		.build();
	
	/* Metodo que define un objeto personalizado (custom) que va a proporcionar
	 * la config, personalizada que va a adquirir el CircuitBreakerFactory 
	 * 
	 * NOTA: Si se quiere tener varias configuraciones para varios Circuit Breaker
	 * para crear  
	 * */
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustom(){
		/*En este metodo se pueden definir un CircuitBreakerfactory, 
		 *ya sea uno de caracter universal (global) o ya sea uno especifico 
		*/		
		
		//Configuracion Global
		return factory -> factory.configureDefault(
			idGlobal -> new Resilience4JConfigBuilder(idGlobal)
				.circuitBreakerConfig(config)
				.build()
		);
		
		//Configuracion especifica (para un determinado Circuit Breaker)
		
		/*return factory -> factory.configure(
			builder -> builder.circuitBreakerConfig(config).build() , "circuit1-id"
		);*/
	}
}
