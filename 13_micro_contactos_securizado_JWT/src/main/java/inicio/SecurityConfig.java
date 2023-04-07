package inicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
	AuthenticationManager auth;
	
	/* NOTAS:
	 * 
	 * A. Formas de crear Listas con métodos estaticos
	 * 		a) con Arrays.asList()
	 * 		b) con List.of()
	 * 
	 * B. Patrón Builder
	 * En vez de usar un constructor para darle todos los datos a un Objeto, 
	 * se van llamando métodos sucesivos (se va configurando un Builder) para ir
	 * configurando propiedades de un objeto
	 * 
	 * C. password()
	 * Forma para describirle a Spring que la contraseña de un User 
	 * no esta encriptada: 
	 * 		.password("{noop}user") 
	 * 		 
	 * D. authorities()
	 * Designación de roles
	 * 
	 * E. build()
	 * Cuando se han configurado las propiedades deseadas para un objeto 
	 * creado de un "patrón Builder", se llama al método  build() 
	 * para devolver dicho objeto
	 */
	@Bean
	public InMemoryUserDetailsManager detailsManager() throws Exception {
				
		List<UserDetails> users = Arrays.asList(
					User.withUsername("user1")
					.password("{noop}user1")
					.authorities("USERS")
					.build(),
					User.builder()
					.username("admin") 
					.password("{noop}admin")
					.authorities("USERS","ADMIN")
					.build()
				);
		return new InMemoryUserDetailsManager(users);
	}
	
	/* la siguiente configuración será para el caso de 
	 usuarios en una base de datos
	  
	public DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/springsecurity?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}*/
	
	/* NOTAS CONEXION A UNA BASE DE DATOS EXTERNA
	 * 
	 * A. JdbcUserDetailsManager vs InMemoryUserDetailsManager
	 * 
	 * Sus diferencias son respecto a cómo obtiene los usuarios y sus respectivos 
	 * roles.
	 * 
	 *	> InMemoryUserDetailsManager: Desde memoria.
	 *	> JdbcUserDetailsManager: Desde una conexión a BD.
	 * 
	 * 
	 * B. JdbcUserDetailsManager para Bases de Datos
	 * 
	 * Para hacer uso de este método, se necesita anotarlo con @Bean. 
	 * Con DataSource obtenemos la conexión a una BD. Con JdbcUserDetailsManager 
	 * obtenemos  los usuarios y sus roles desde Bases de Datos; para ello, 
	 * hacemos uso de consultas que nos devuelvan:
	 * 
	 * [setUsersByUsernameQuery()] el usuario, contraseña y habilitado (enabled)
	 * [setAuthoritiesByUsernameQuery()] el usuario y rol que tiene asignado.
	 * 
	 * NOTA: Los nombres de las columnas en las consultas son indistintas.
	 */
	
	/*la siguiente configuración será para el caso de 
	 usuarios en una base de datos
	@Bean
	public JdbcUserDetailsManager usersDetailsJdbc() {
		JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(datasource());
		jdbcDetails.setUsersByUsernameQuery("select username, password, enabled"
	       	+ " from users where username=?");
		jdbcDetails.setAuthoritiesByUsernameQuery("select username, authority "
	       	+ "from authorities where username=?");
		return jdbcDetails;
	}*/
		
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
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/contactos").hasAnyAuthority("ADMIN")
			.antMatchers("/contactos").authenticated()
			.and()
			//.httpBasic();
			.addFilter(new JWTAuthorizationFilter(auth) );
		return http.build();
	}
	
	/* METODO DE CONFIGURACION DE AuthenticationManager
	 * 
	 * Por el principio de Inyeccion de Dependencias, Spring
	 * nos inyectará el " AuthenticationConfiguration "; este objeto
	 * ua ñp crea Spring Security a tráves del starter 
	 * (de seguridad)
	 * 
	 */
	@Bean
	public AuthenticationManager authManager
	(AuthenticationConfiguration conf) throws Exception
	{
		auth = conf.getAuthenticationManager();
		return auth;
	}
}
