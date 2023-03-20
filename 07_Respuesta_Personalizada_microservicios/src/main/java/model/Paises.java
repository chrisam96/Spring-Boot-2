package model;

public class Paises {
	private String nombre;
	private String capital;
	private Integer poblacion;
	private String bandera;
	
	public Paises() {
	}

	public Paises(String nombre, String capital, Integer poblacion, String bandera) {
		this.nombre = nombre;
		this.capital = capital;
		this.poblacion = poblacion;
		this.bandera = bandera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}
	
	
}
