package spring_boot.mapeadoObjetos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MapeandoObjetosApplicationTests {
	
	/*
	 * JUNIT
	 * > Spring Boot incorporaJunit versión Júpiter 
	 * para testear microservicios.
	 * 
	 * > Al crear un proyecto, se genera una clase de prueba 
	 * en src/test/java.
	 * 
	 * > Se pueden realizar invocaciones al microservicios
	 *  través del objeto MockMvc
	 */
	
	/*
	 * ANOTACIONES DE JUNIT
	 * 
	 * > @AutoConfigureMockMvc
	 * Nos ayuda a que Spring Boot conefigure el objeto MockMvc
	 * 
	 * >@TestMethodOrder(OrderAnnotation.class)
	 * Nos sirve para configurar la anotacion @Order
	 * 
	 * >@Order(0)
	 * Permite establecer un orden de ejecución de los métodos
	 */
	
	/*
	 * MockMvc
	 * Este objeto nos permite hacer las pruebas del servicio
	 */
	@Autowired
	MockMvc mock;
	
	@Test
	@Order(0)
	void getTesting() throws Exception{
		mock.perform(get("/cursos")).andDo(print());
	}
	
	@Test
	@Order(1)
	void deleteTesting() throws Throwable{
		mock.perform(delete("/del/Python")).andDo(print());
	}
	
	@Test
	@Order(2)
	void postTesting() throws Throwable{
		mock.perform(post("/post")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content("{\n"
					+ "  \"nombre\": \"Angular 10\",\n"
					+ "  \"horario\": \"tarde\",\n"
					+ "  \"duracion\": 10\n"
					+ "}"))
		.andDo(print());
	}
	
	@Test
	@Order(3)
	void updategetTesting() throws Throwable{
		mock.perform(put("/update")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content("{\n"
					+ "  \"nombre\": \"Angular 10\",\n"
					+ "  \"horario\": \"mañana\",\n"
					+ "  \"duracion\": 12\n"
					+ "}"))
		.andDo(print());
	}

}
