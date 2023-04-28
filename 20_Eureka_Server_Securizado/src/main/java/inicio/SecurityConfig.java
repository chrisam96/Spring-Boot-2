package inicio;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
	}

	public SecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}
	
	//DEFINICION DE POLITICAS DE SEGURIDAD o DE ACCESO A RECURSOS
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		//super.configure(http);
		http.csrf().disable();//-->desactivacion de proteccion entre dominios
		http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin").roles("ADMIN");
	}
	
	
}
