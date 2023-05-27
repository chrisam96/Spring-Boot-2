package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*[[[Servidor Zuul]]]
 * 
 * >Es un Servidor que proporciona un punto de acceso único 
 * (para el cliente [FrontEnd]) a un conjunto de servicios.
 * 
 * > También nos permite el acceso a los servicios BackEnd a través de Eureka
 * 
 * [[[Dependencias de Zuul]]]
 * Se crea como un microservicio Spring Boot pero requiere las dependencias:
 * 		Eureka Discovery Client
 * 		Zuul [Maintenance]
 * 		Spring Web
 * 
 * [[[Configuración del Servidor Zuul]]]
 * 
 * > En el archivo properties o .yml: 
 * A través de la propiedad zuul.routes, se indican las rutas 
 * de los servicios que centraliza y los mapeos a direcciones 
 * reales URL (a traves de la propiedad: url) 
 * o virtuales vía Eureka (a traves de la propiedad: serviceld).
 *
 * Las propiedades de Zuul tienen la siguiente estructura
 * zuul.routes.[id].path
 * zuul.routes.[id].serviceId||url
 * 
 * Donde::
 * path: Es el path/direccion que intercepta el servidor Zuul 
 * serviceId: Servicio al que se va a redirigir usando Eureka
 * url: Servicio donde se va a redirigir usando su URL
 * 
 * > La Clase main anotada con @EnableZuulProxy
 * 
 * [[[Acceso a servicios a tráves de Zuul]]]
 * 
 * Las peticiones se lanzan al servidor Zuul (desde el front o un cliente),
 * que luego las redirige al servicio correspondiente (BackEnd).
 */

@EnableZuulProxy
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("*", config);
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
	
	/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }
    */
}
