package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javafx.scene.media.Media;
import spring_boot.mapeadoObjetos.model.Cursos;
import spring_boot.mapeadoObjetos.model.CursosXML;

@RestController
public class CursosController {
	
	/*
	 * Resolución del problema del escaneo de paquetes (@COMPONENT_SCAN) 
	 * 
	 * Para solventar eso, se debe implementar debajo de la anotación 
	 * @SpringBootApplication del método main (de la clase principal) 
	 * con la otra anotación: 
	 * 
	 * @ComponentScan (basePackage = {"name_of_package1"}, {"name_of_packageN"} )
	 */
	private List<Cursos> cursos;
	private List<CursosXML> cursosXml;
	
	
	/* JACKSON
	 * Jackson: Se encarga de mapear (transofrmar) objectos Java (Java Bean)
	 * a JSON y vicerversa.
	 */
	@GetMapping(value="curso", produces=MediaType.APPLICATION_JSON_VALUE)
	public Cursos getCurso() {
		return new Cursos("Java", "Mañana", 100);
	}

	
	/*
	 * POST CONTRUCT
	 * @PostConstruct: Sirve para que se invoque un método después de haberse 
	 * iniciado el Controller., es decir, después de haber instanciado al 
	 * constructor del mismo.
	 * 
	 * Después de haberse instanciado el Controller se ejecuta/inicia el método.
	 * 
	 * Es un método que se ejecuta después del constructor.
	 * */
	@PostConstruct
	public void init() {
		cursos = new ArrayList<>();
		cursos.add(new Cursos("Spring", "Tarde", 25));
		cursos.add(new Cursos("Spring Boot", "Tarde", 20));
		cursos.add(new Cursos("Python", "Tarde", 30));
		cursos.add(new Cursos("Java EE", "Fin de semana", 50));
		cursos.add(new Cursos("Java Básico", "Mañana", 30));
		
		cursosXml = new ArrayList<>();
		cursosXml.add(new CursosXML("Spring", "Tarde", 25));
		cursosXml.add(new CursosXML("Spring Boot", "Tarde", 20));
		cursosXml.add(new CursosXML("Python", "Tarde", 30));
		cursosXml.add(new CursosXML("Java EE", "Fin de semana", 50));
		cursosXml.add(new CursosXML("Java Básico", "Mañana", 30));
		
	}
	
	@GetMapping(value = "cursos", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Cursos> getAllCursos(){
		return cursos;
	}
	
	@GetMapping(value = "curso/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public List<Cursos> buscarCursos(@PathVariable("name") String nom){
		List<Cursos> aux = new ArrayList<>();
		/*for (Cursos curso : cursos) {
			if (curso.getNombre().contains(null)) {
				aux.add(curso);
			}
		}*/
		
		//Aplicando API Collections de JAVA 1.8 
		aux = (List<Cursos>) cursos.stream()
				.filter( (Cursos t) -> t.getNombre().contains(nom) )
				.collect(Collectors.toList());
		
		return aux;
	}
	
	/*
	 * DELETE ANNOTATION
	 * Asociado a operaciones de eliminación de recursos.
	 * Se usa con la anotación de @DeleteMapping
	 */
	@DeleteMapping(value = "/del/{nom}")
	public void eliminarCursos(@PathVariable(name = "nom") String nombre ) {
		cursos.removeIf(crs -> crs.getNombre().equals(nombre));
	}
	
	/*
	 * POST ANNOTATION
	 * Asociado a operaciones de inserción de recursos.
	 * Se usa con la anotación de @PostMapping
	 * 
	 * > El cuerpo de la petición se envía en formato JSON, 
	 * XML u otros.
	 * 
	 * > Mediante el atributo "consumes" de @PostMapping se 
	 * indica el formato del body de la petición  
	 *  
	 * > El cuerpo se mapea a un bean mediante @RequestBody
	 */
	@PostMapping(value = "post", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cursos> altaCurso(@RequestBody Cursos crs) {
		cursos.add(crs);
		return cursos;
	}
	
	/*
	 * PUT ANNOTATION
	 * Asociado a operaciones de actualización de recursos.
	 * Se usa con la anotación de @PutMapping
	 * 
	 * > El cuerpo de la petición se envía en formato JSON, 
	 * XML u otros.
	 * 
	 * > Mediante el atributo "consumes" de @PutMapping se 
	 * indica el formato del body de la petición  
	 *  
	 * > El cuerpo se mapea a un bean mediante @RequestBody
	 */
	@PutMapping(value = "update", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Cursos actualizarCurso(@RequestBody Cursos crs) {
		Cursos foundIt =  null;
		
		for (int i = 0; i < cursos.size(); i++) {
			if (cursos.get(i).getNombre().equals(crs.getNombre())) {
				cursos.set(i, crs);
				foundIt = cursos.get(i);
			}
		}
		return foundIt;
	}
	
	/*
	 * XML JSON
	 * A tráves de @XmlRootElement, Spring indica que una clase Java Bean 
	 * se mapea/transforma como XML.
	 * 
	 * Dentro de las @notaciones de los verbos, en el campo"produces" 
	 * se pone como: "produces = MediaType.APPLICATION_XML_VALUE"
	 */
	@GetMapping(value="xml", produces = {MediaType.APPLICATION_XML_VALUE})
	public Cursos getCursoXML(){
		return cursos.get(0);
	}

	@GetMapping(value = "xmlista", produces = {MediaType.APPLICATION_XML_VALUE})
	public List<Cursos> getAllCursosXML(){
		return cursos;
	}
	
	@GetMapping(value = "xml/{name}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public List<Cursos> buscarCursosXML(@PathVariable("name") String nom){
		//Aplicando API C1ollections de JAVA 1.8
		List<Cursos> aux = (List<Cursos>) cursos.stream()
				.filter( (Cursos t) -> t.getNombre().contains(nom) )
				.collect(Collectors.toList());
		
		return aux;
	}
}
