package service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.Paises;

@Service
public class PaisesServiceImpl implements PaisesService{

	String URL = "https://restcountries.com/v2/all";
	//String URL = "https://restcountries.com/v3.1/all";
	
	/*Se agrega para hacer uso del RestTemplate para
	 * el consumo de otro de Microservicios
	*/
	@Autowired
	RestTemplate template;
	
	/*LIBRERIA JACKSON
	 * 
	 * Permite manipular el documento JSON de respuesta, y así,
	 * tener acceso a elementos individualizados.
	 * 
	 * Clases principales:
	 * 
	 * 		ObjectMapper: A través del método readTree0 
	 * 		proporciona acceso a (todo) el documento 
	 * 
	 * 		ArrayNode: Representa un array de elementos del
	 * 		documento
	 * 
	 * 		ObjectNode: Representa un nodo del documento
	 * 
	 */
	@Override
	public List<Paises> obtenerPaises() {
		//Hace la petición a otro microservicio
		String resultado = template.getForObject(URL, String.class);
		
		List<Paises> paises = new ArrayList<>();
		
		//Instancia de clases de lib Jackson
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode;
		
		String nombre, capital, bandera;
		int poblacion;
		
		try {
			/*ObjectMapper obtiene el documento de todo el JSON y lo
			 * parsea a un arreglo de Nodos (ArrayNode) mediante readTree()
			 * 
			 * ObjectNode es un elemento de ese arreglo (ArrayNode)
			 */
			arrayNode = (ArrayNode) mapper.readTree(resultado);
			
			for (Object obj : arrayNode) {
				
				//Del nodo JSON, extraemos las propiedades que nos interesan				 
				ObjectNode json = (ObjectNode) obj;
				
				/*Debido a un error de NulllPointerException en las propiedades 
				 * de la Antartida, use la clase Optional<>
				 */
				
				Optional<JsonNode> opNom = Optional
						.ofNullable(json.get("name"));
				nombre = opNom.isPresent() ? opNom.get().asText() : "No tiene";
				
				Optional<JsonNode> opCapi = Optional
						.ofNullable(json.get("capital"));
				capital = opCapi.isPresent() ? opCapi.get().asText() : "No Aplica";
				
				Optional<JsonNode> opPob = Optional
						.ofNullable(json.get("population"));
				poblacion = opPob.isPresent() ? opPob.get().asInt() : 0;
				
				Optional<JsonNode> opBan = Optional
						.ofNullable(json.get("flag"));
				bandera = opBan.isPresent() ? opBan.get().asText() : "No existe";
				
				
				paises.add(
					new Paises(
						
						  nombre,	//json.get("name").asText("Sin nombre"), 
						  capital,	//json.get("capital").asText("No Aplica"),
						  poblacion,//json.get("population").asInt(0), 
						  bandera	//json.get("flag").asText("No tiene")
						 
					)
				);
				
				System.out.println(nombre+"|"+capital+"|"+poblacion+"|"+bandera);
				/*
				 * System.out.println(json.get("name").asText("Sin nombre") + "|" +
				 * json.get("capital").asText("No Aplica")+ "|" +
				 * json.get("population").asInt(0) + "|" + json.get("flag").asText("No tiene"));
				 */
			}
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return paises;
	}

	@Override
	public List<Paises> buscarPaises(String name) {
		/*Recupera los países cuyo nombre contiene la 
		 * combinación de caracteres recibidas
		 * 
		 */
		return obtenerPaises()
				.stream()
				.filter(pais -> pais.getNombre().contains(name))
				.collect(Collectors.toList());
	}
 
}
