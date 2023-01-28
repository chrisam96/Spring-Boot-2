package spring_boot.mapeadoObjetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * Resolución del problema del escaneo de paquetes (@COMPONENT_SCAN)  
 * 
 * Para solventar eso, se debe implementar debajo de la anotación 
 * @SpringBootApplication del método main (de la clase principal) 
 * con la otra anotación: 
 * 
 * @ComponentScan (basePackage = {"name_of_package1"}, {"name_of_packageN"} )
 */
@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class MapeandoObjetosApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MapeandoObjetosApplication.class, args);
	}

}
