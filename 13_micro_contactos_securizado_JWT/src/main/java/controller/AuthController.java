package controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static util.Constantes.*;

/*Controller para la Autenticación
 * 
 * Se debe implementar un controller específico encargado de la 
 * autenticación y generación del token. 
 * 
 * Dicho Controller se auxiliará de clases como Authentication, 
 * AuthenticationManager y UsernamePasswordAuthenticationToken 
 * entre otros.
 */
@RestController
public class AuthController {
	
	//Inyección de AuthManager
	//Nos va a permitir autenticar al usuario
	public AuthenticationManager auth;

	//	Constructor vacio
	/*public AuthController() {
	}*/

	/*
	 * Spring ya sabe que tiene que inyectar (AuthenticationManager)
	 * esta dependencia al instanciar este constructor
	 */
	public AuthController(AuthenticationManager auth) {
		this.auth = auth;
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("user") String user, 
			@RequestParam("pwd") String pwd) 
	{
		/*
		 * <<<Authentication>>>
		 * Objeto generado por Spring Security para saber si un usuario
		 * esta registrado o en el servicio.
		 * 
		 * <<<UsernamePasswordAuthenticationToken>>>
		 * Busca el registro del usuario al microservicio 
		 */
		Authentication autenticacion = auth.authenticate(new UsernamePasswordAuthenticationToken(user, pwd));
		
		//Si el usuario esta autenticado, genera el TOKEN
		if (autenticacion.isAuthenticated()) {
			return getToken(autenticacion);
		} else {
			return "No Autenticado";
		}
	}

	/*<<<Generación del JWT>>>
	 * 
	 * Con la librería JJWT y el método builder(), 
	 * se irán asignando propiedades al token que se incluirán en 
	 * el Payload del mismo
	 * 
	 * <<<Claim>>>
	 * 
	 * Un Claim son piezas de información acerca de un tema (especifico).
	 * 
	 * Los Claims son meta-metadatos pero también se pueden agregar
	 * datos personalizados a tráves de este método.
	 * 
	 * Los Claim aparecen en pares clave-valor. Un valor "null"
	 * removera la propiedad de los Claims.
	 * 
	 * 
	 * << Keys.hmacShaKeyFor(byte[] bytes) >>
	 * 
	 * Crea una nueva instancia de SecretKey para usarse con 
	 * algoritmos HMAC-SHA con base al byte array de su parametro
	 * (o sea, la llave especificada).
	 */
	private String getToken(Authentication autenticacion) {
		/* En el body del Token se incluye:
		 * - fecha de creacion
		 * - nombre de usuario
		 * - roles a los que pertenece
		 * - fecha de caducidad
		 * - datos de la firma (Secret Key || Signature)
		 */
		String token = 
			Jwts.builder() 
			.setIssuedAt(new Date())//Fecha de creacion
			.setSubject(autenticacion.getName())//usuario
			.claim(  
				"authorities",//roles 
					autenticacion		 //obtencion de roles
					.getAuthorities()
					.stream() 			//useo de la API Stream
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList())
			)
			//fecha de expiracion
			.setExpiration(new Date(System.currentTimeMillis() + TIEMPO_VIDA))
			//clave y algoritmo para firma del Secret Key
			.signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
			.compact();//generacion del token
		
		return token;
	}
	
	/*NOTAS DE Claims
	 * 
	 * << Jwts.builder().claim(String, Object) vs 
	 * Jwts.builder().setClaims(claims) >>>
	 * 
	 *  La definicion de "Jwts.builder().claim(String, Object)" es
	 *  una convención de simplificación de la otra instancia. para
	 *  reducción de codigo sin necesidad de crear objetos Claims.
	 *  
	 *  Reduccion::
	 *  String jwt = Jwts.builder().claim("aName", "aValue").compact();
	 *  
	 *  
	 *  Instancia Claims::
	 *  Claims claims = Jwts.claims().put("aName", "aValue");
	 *  String jwt = Jwts.builder().setClaims(claims).compact();
	 *  
	 *  
	 *  
	 *  << Jwts.builder.addClaims( Map<String, Object> ) vs
	 *  Jwts.builder().setClaims( Map<String, ?> ) >>
	 *  
	 *  addClaims() agrega (junto a los ya existentes) 
	 *  todos los pares nombre-valor (claims) a
	 *  los JSON Claims dentro del Payload.
	 *  
	 *  setClaims() coloca un conjunto de JSON Claims dentro del
	 *  Payload. Esto puede sustituirClaims existentes.
	 *  
	 */
	 
	
}
