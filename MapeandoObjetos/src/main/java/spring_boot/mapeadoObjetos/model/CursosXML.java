package spring_boot.mapeadoObjetos.model;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * XML JSON
 * A tráves de @XMLRootElement, Spring indica que una clase Java Bean 
 * se mapea/transforma como XML.
 * 
 * Dentro de las @notaciones de los verbos, en el campo"produces" 
 * se pone como: "produces = MediaType.APPLICATION_XML_VALUE".
 * 
 * Para usar la dependencia de Jackson se necesita la siguiente 
 * dependencia en Maven:
 * 
 * 		<dependency>
        	<groupId>com.fasterxml.jackson.dataformat</groupId>
        	<artifactId>jackson-dataformat-xml</artifactId>
        	<version>2.13.4</version>
      	</dependency>
 */
@XmlRootElement
public class CursosXML {
	private String nombre;
	private String horario;
	private int duracion;
	
	public CursosXML() {
	}
	
	

	public CursosXML(String nombre, String horario, int duracion) {
		this.nombre = nombre;
		this.horario = horario;
		this.duracion = duracion;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
