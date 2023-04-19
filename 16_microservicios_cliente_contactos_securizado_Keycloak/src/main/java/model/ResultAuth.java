package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultAuth {
	/*@JsonProperty
	 * 
	 * Para mappear (o asociar) una propiedad de un POJO con una
	 * propiedad de un JSON, se puede hacer a traves de la anotacion
	 * de @JsonProperty
	 * 
	 * NOTAS DE MAPPEO DE JSON EN POJOs
	 * 
	 * Se puede mappear las propiedades de un JSON hacia una 
	 * propiedad POJO automaticamente siempre y cuando tengan
	 * el mismo nombre ambas partes.  
	 */
	@JsonProperty(value = "access_token")
	private String access_token;
	
	//CONSTRUCTORES
	public ResultAuth() {
	}

	public ResultAuth(String access_token) {
		this.access_token = access_token;
	}

	/*GETTERS & SETTERS*/
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
}
