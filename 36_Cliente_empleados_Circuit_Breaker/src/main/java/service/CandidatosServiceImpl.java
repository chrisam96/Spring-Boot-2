package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Candidato;

@Service
public class CandidatosServiceImpl implements CandidatosService {
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	CircuitBreakerFactory factory;
	
	private String URL = "http://localhost:9035/"; 
	
	@Override
	public List<Candidato> candidatos(String puesto) {
		//Aqui se crea un CircuitBreaker  a partir un factory
		CircuitBreaker circuit = factory.create("circuit1");
		
		//Aqui se aplica el metodo "run()" del CIRCUIT BREAKER
		return circuit.run(
			/* Hace la llamada como se haria normalmente al microservicio servidor 
			 * y devuelve un resultado.
			 * */
			() -> {
				List<Candidato> lista = Arrays.asList( 
					template.getForObject(URL + "empleados", Candidato[].class)
				);
				return lista.stream().
					filter(
						cand -> cand.getPuesto().equalsIgnoreCase(puesto)
					).collect(Collectors.toList());
			}, 
			//En caso de fallas, se envía esto
			throwable -> {
				List<Candidato> error = new ArrayList<Candidato>();
				error.add(new Candidato("Error 404", -1, "No es posible obtener datos "));
				return error;
			}
		);
	}

}
