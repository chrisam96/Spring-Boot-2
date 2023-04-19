package util;

public class Constantes {
	
	/*
	 * NOTA sobre la secret key
	 * 
	 * Con la nueva versión de la líbrería JJWT, 
	 * la clave deberá tener al menos 86 dígitos.
	 */
	
	//SECRET KEY
	public static final String CLAVE="12345678912345678901234567890123456789012345678901234567890123456789012345678901234567";
	
	//Duracion de 1 dia en microsegundos
	public static final long TIEMPO_VIDA = 86_400_000; // 1 dia
	
	//Encabezado(Header) donde se guarda el token
	public static final String ENCABEZADO = "Authorization";
	
	//Prefijo que va antes del token
	public static final String PREFIJO_TOKEN = "Bearer ";
}
