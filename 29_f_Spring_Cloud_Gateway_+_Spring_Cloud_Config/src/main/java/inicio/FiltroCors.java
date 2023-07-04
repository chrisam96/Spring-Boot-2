package inicio;

/*NOTA: Usar las clases de los paquetes que contengan el paquete "reactive".*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Configuration
public class FiltroCors {
	 private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN";
	 private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
	 private static final String ALLOWED_ORIGIN = "*";
	 
	 /* [[[ Problema de CORS y Filtro de Spring Gateway ]]]
	  * Como si fuese por "defecto" las peticiones GET no se ven afectadas por
	  * las politicas CORS, sin embargo, otros verbos si.
	  * 
	  * El servidor Gateway bloquea las peticiones POST (y otrs verbos excepto 
	  * GET) procedentes de un Script (del cliente).
	  * 
	  * Y aunque los servicios tengan la anotacion "@CrossOrigin" (que permite
	  * el acceso desde cualquier origen), siguen siendo bloqueadas las 
	  * peticiones (que incluyen ademas objetos JSON) por Gateway. 
	  * 
	  * solucion::
	  * Se tiene que añadir un componente (@Bean) de filtro que se registre en
	  * el servidor Gateway para que no bloquee las peticiones.
	  * */
	 @Bean
	 public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) 
		-> 
		{
			/*NOTA: Todas las clases deben ser importadas del package "reactive".
			 * Puede que se trabaje con programación reactiva (Spring Boot 3)*/
			ServerHttpRequest request = ctx.getRequest();
			
			/*Compara si en la petición hay una petición del tipo CORS
			 * En caso de haber, se le agregarán las cabeceras pertinentes*/
			if (CorsUtils.isCorsRequest(request)) {
				ServerHttpResponse response = ctx.getResponse();
				HttpHeaders headers = response.getHeaders();
				headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
				headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
				headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
			}
			return chain.filter(ctx);
		};
	 }
}

/* [[[Spring Cloud Gateway]]]
 * 
 * > Al igual que Zuul, proporciona un punto de acceso 
 * único a un conjunto de servicios (BackEnd).
 * > Adecuado para trabajar con conexiones de larga
 * duración, como Websockets.
 * 
 * [[[Creacion del server Gateway]]]
 * > Se crea como microservicio Spring Boot que requiere de
 * las dependencias Gateway y Discovery Client:
 * 
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-gateway</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
 *
 * ::: NOTAS :::
 * > No se debe incluir Spring Web, ya que 
 * Spring Web no es compatible con el servidor Gateway
 * 
 * > La clase main no tiene que tener ninguna anotación especial
 */ 


/*	[[[ Arquitectura ]]]
 * 
 * > :::spring.cloud.gateway.routes::: 
 * A través de los enrutados (routing) se
 * define lo que se debe hacer con una petición específica.
 * 
 * > ::: id :::
 * Identificador del enrutado
 * 
 * > ::: uri :::
 * Dirección del servicio al que se va a redirigir 
 * 
 * NOTA::
 * Para dirigir peticiones a ciertas URI a través de Eureka 
 * (y otras proxy) en vez de "http" se usa el protocolo de "lb".
 * 
 * > ::: predicates :::
 * El predicado (predicates) establece la condición 
 * (path, cookies, encabezados,etc.), que debe cumplir una petición 
 * para ser enrutada a un determinado destino.
 * 
 * NOTA::
 * Gateway suele concadenar el valor de "Path" después del valor de
 * la URI en su url (antes de hacer el redireccionamiento). 
 * Para quitar ello, se le debe agregar el filtro de RewritePath.
 * 
 * 	filters:
 * 		RewritePath: [valor1], [valor2]
 * 
 * > ::: filters :::
 * Antes de dirigir la petición al destino, se
 * le pueden aplicar uno o varios filtros que
 * modifiquen alguna característica de la petición.
 * >
 * */