package model;

public class FichaProducto {

	private String nombre;
	private int codigo;
	
	public FichaProducto() {
	}

	public FichaProducto(String nombre, int codigo) {
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
