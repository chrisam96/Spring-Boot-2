package inicio;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
			
	//DEFINICION DE POLITICAS DE SEGURIDAD
	/*	POLITICAS DE ACCESO A RECURSOS
	 * 
	 * 	A. Explicación de CSRF()
	 * 
	 * Es una especie de token que se usa para "SingleSignal", es decir, 
	 * para que el usuario no tenga que estar autenticandosé varias veces
	 * 
	 * B. csrf().disable()
	 * 
	 * Spring recomienda que cuando se va a implementar un microservicio, 
	 * con el que se va a interaccionar desde otro microservicio 
	 * esta propiedad debería estar desactivada, para evitar  que pueda interferir con algún sistema de autenticación interno y conflictos de seguridad en el micro cliente.
	 * 
	 * C. authorizeRequests() (Notas de la versión de Spring)
	 * 
	 * Se usa para ir configurando las autorizaciones a ciertos recursos, 
	 * a través de métodos antMatchers().
	 * 
	 * En las versiones de Spring Boot, los métodos cambian:
	 * 
	 * 	SB v2: authorizeRequests()
	 * 	SB v3: authorizeHttpRequest()
	 * 
	 * D. antMatchers()
	 * Con este método indicamos que URLs van a ser protegidas y bajo que roles 
	 * de usuario. Se pueden proteger URLs, métodos HTTP o una combinación de ambos.
	 * 
	 * E. antMatchers() (Notas de la versión de Spring).
	 * 
	 * A partir de Spring Boot 3 el método es "requestMatchers()" 
	 * en lugar de "antMatchers()". 
	 * 
	 * F. hasAnyRole()
	 * 
	 * Método para permitir que usuarios de ciertos roles tengan acceso a 
	 * los recursos (URLs) protegidos.
	 * 
	 * G. hasRole() vs hasAuthority()
	 * Cumplen las mismas funciones, dar accesos URL a un rol particular. 
	 * Sin embargo, hasRole() agrega automaticamente el prefijo de rol "ROLE_"
	 * a cada parametro. En caso de no querer tenerlo, use hasAuthority()
	 * 
	 * H. hasAnyRole() vs hasAnyRole() && hasAuthority() vs hasAnyAuthority()
	 * Los métodos que CARECEN DE la raíz "Any" solo permiten un rol en especifíco.
	 * Los métodos que TIENEN "Any" permiten cualquiera de sus roles de su lista. 
	 * 
	 * I. authenticated()
		
		Usado para especificar que las direcciones URL están permitidas para 
		cualquier usuario autenticado. 
		
		Con esto la URL ya no será de acceso libre.	
		
		J. Doble * y antMatchers()

		Si se quiere securizar una URL y todas las derivaciones de esa misma URL 
		(ya sea que tenga parámetros, variable, étc.), se tendría que poner 
		doble * al final de la URL.

		Ejemplo:
		
		antMatchers("/contactos/**").authenticated();
		
		K. and() 
		
		Devuelve el objeto HttpSecurity con todas las configuraciones previas 
		establecidas.
		
		L. build()

		Transforma un objeto HttpSecurity con todas sus configuraciones a un objeto 
		SecurityFilterChain.
		
		M. httpBasic()

		Usado para determinar que queremos que se haga una autenticación básica.

		NOTA: NO devuelve un SecurityFilterChain.
				
		*/
	
	/* SUSTITUCIÓN DE httpBasic() por JWT 
	 * 
	 * La autenticación se realiza a tráves del token, y por lo tanto,
	 * se hará por un filtro encargado de realizar la autorización
	 * a paritr del token.
	 * 
	 * Se modifica el proceso de autorización ya que 
	 * se extrae la información del token y se pasa a Spring Security
	 * 
	 * A. addFilter(Filter)
	 * 
	 * 
	 */
	//DEFINICION DE POLITICAS DE SEGURIDAD
	/*
	 * El método "configure(HttpSecurity)" es heredado de KeycloakWebSecurityConfigurerAdapter
	 * quién completará la configuración
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		http.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST, "/contactos").hasAnyAuthority("ADMIN","ROLE_ADMIN",
				"ROLE-ADMIN","ROLE ADMIN","USERS")
		.antMatchers("/contactos").authenticated();
	}

	//ESTRATEGIA DE LA SESION DE AUTENTICACION
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}
	
	//REGISTRO DE KeycloakAuthenticationProvider CON EL AuthenticationManager
	/*
	 * Se inyecta el AuthenticationManagerBuilder que manejara la autenticacion,
	 * es decir, es el Proveedor de autenticación de Keycloak
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		KeycloakAuthenticationProvider keycloakAuthenticationProvider = super.keycloakAuthenticationProvider();
		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(keycloakAuthenticationProvider);
	}
	
}
