package inicio;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*CLASE ADICIONAL DE CONFIGURACION DE SEGURIDAD
 *
 * En una clase independiente a KeycloakWebSecurityConfigurerAdapter, 
 * se define la creación del objeto KeycloakConfigResolver para cargar el
 * adaptador de Spring Boot y así leer las propiedades de application.properties.

NOTA: Es necesaria su creacion independiente, debido a que chocaría con uno
de los atributos de la clase heredada KeycloakSpringBootConfigResolver
(@Bean: KeycloakSpringBootConfigResolver) 
 */
@Configuration
public class KeyCloakConfig {
	
	@Bean
	public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
}