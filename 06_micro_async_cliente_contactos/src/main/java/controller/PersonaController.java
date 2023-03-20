package controller;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Persona;
import service.AccesoService;

@RestController
public class PersonaController {

	@Autowired
	AccesoService servicio;
	
	

	@GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(
			@PathVariable("nombre") String nombre,
			@PathVariable("email") String email,
			@PathVariable("edad") int edad
			){
		//Se crea el objeto a registrar
		Persona persona =  new Persona(nombre, email, edad);
		
		/* El método "servicio.llamadaServicioAsync(persona)" 
		 * se ejecuta de forma asíncrona
		 * 
		 */
		CompletableFuture<List<Persona>> resultado = servicio.llamadaServicioAsync(persona);
		
		//Simulnado que se va a hacer otra tarea en lo que
		//trabaja el CompletableFuture (de forma asíncrona)
		for (int i = 0; i < 50; i++) {
			System.out.println("(SIMULANDO) esperando ...");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/* Con get() recuperamos el objeto encapsulado 
		 * del CompletableFuture<T>
		 * 
		 * NOTAS
		 * A. Mientras se hace la llamada a los métodos asincronos
		 * el controller va ejecutando los demás bloques de código
		 * 
		 * B. Qué va a pasar si cuando hemos terminado de hacer 
		 * otras tareas (del mismo bloque de código) 
		 * llamamos a "get()" y aún no tiene el resultado
		 * (de la ejecución del método asíncrono)?
		 * 
		 * Nada, cuando se termine ese trabajo se obtendrá la 
		 * respuesta y en este caso se devolverá al cliente
		 * 
		 * 
		 */
		try {
			return resultado.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Esta funcionalidad se fue de Sabatico
	 * 
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarPorEdades
		(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		Persona[] personas = template.getForObject(urlBase+"/contactos", Persona[].class);
		return Arrays.stream(personas)
				.filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
				.collect(Collectors.toList());
	}
	*/
}