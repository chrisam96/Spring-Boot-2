package inicio;

import static util.Constantes.CLAVE;
import static util.Constantes.ENCABEZADO;
import static util.Constantes.PREFIJO_TOKEN;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

//import io.jsonwebtoken.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		/* Se llama al constructor del padre para que se hagan todas las 
		 * configuraciones e inicializaciones pertinentes.
		 */
		super(authManager);
	}

	/*
	 * En dolnternalFilter() se decodifica el token, se verifica que no ha 
	 * sido alterado y se obtiene la información del usuario, pasando el control
	 * a Spring Security para que proceda a la autorización
	 */
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain) 
		throws IOException, ServletException
	{
		/* :::NOTA:::
		 * El FILTRO se va a ejecutar con cada petición
		 */
		
		/* >Se extrae el encabezado donde viene el token, es decir Authorization */ 		
		String header = request.getHeader(ENCABEZADO);
		
		 /* .:.NOTAS.:.
		 * 
		 * > Si el token (header de Authorization) es NULL, 
		 * puede tratarse de un proceso de autenticación
		 * 
		 * > Si falta el header de "Authorization" (o sea, NULL) o
		 * viene dicho header pero no tiene el prefijo "Bearer " (es decir, no es 
		 * el del token JWT)
		 * 
		 * Entonces, no se le hace nada al token y 
		 * se pasa al siguiente filtro de cadena (FilterChain.doFilter()) o 
		 * al destino final de la petición, sea el caso.
		 * */
		if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
			chain.doFilter(request, response);
			return;
		}
		
		/*> Si se salta el IF anterior es porque el token viene en la petición 
		 *
		 *> Se obtienen los datos de autenticación a partir del objeto request (HttpServletRequest).
		 *
		 *> En el objeto de UsernamePasswordAuthenticationToken se nos da el usuario y
		 *roles a los que pertenece (entre otra informacion).
		 */
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		
		
		/* >Con los datos de autenticación, se pasan al contexto de seguridad 
		 * de Spring Security (SecurityContextHolder.getContext().setAuthentication())
		 * 
		 * >Con estos datos, se delega el proceso de autorización a Spring Security.
		 */
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication
		(HttpServletRequest request ) 
	{
		//El token viene en el header de la petición
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			//Se procesa el token y se recupera el usuario y los roles.
			Claims claims 
				= Jwts.parserBuilder()
				.setSigningKey(CLAVE.getBytes())
				.build()
				//El "replace" quita el 'Bearer '  del token
				.parseClaimsJws( token.replace(PREFIJO_TOKEN, "") ) 
				//Con getBody() sacamos las credenciales (Claims) del Payload
				.getBody();
			//Obtenemos el usuario
			String user = claims.getSubject();
			//Obtenemos los roles
			List<String> authorities = (List<String>) claims.get("authorities");
			
			if (user != null) {
				
				/*Con las credenciales, se procede a crear un objeto 
				 * UsernamePasswordAuthenticationToken para que Spring Security
				 * proceda a la autorizacion*/
				return new UsernamePasswordAuthenticationToken
						(user, null, authorities.stream()
								.map(SimpleGrantedAuthority::new)
								//.map(/*String*/ name -> new SimpleGrantedAuthority(name) )
								.collect(Collectors.toList()));
			}
			
			return null;
		}
		return null;
	}
}
