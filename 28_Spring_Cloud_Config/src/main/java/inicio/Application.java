package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/* [[[SPRING CLOUD CONFIG]]]
 * 
 * Centraliza la informacion de configuracion de un conjunto 
 * de microservicios dentro de un repositorio GitHub
 * 
 * ##################################################################################
 * 
 * [[[Creacion del Servidor S.C.C]]]
 * 
 * > Cuando es el SERVIDOR se requiere:
 * 
 * I. La dependencia:
 * <dependency>
   		<groupId>org.springframework.cloud</groupId>
    	<artifactId>spring-cloud-config-server</artifactId>
   </dependency>
   
 * 
 * II. La anotacion @EnableConfigServer en la clase main,
 * la cual lo habilita como servidor de configuracion.
 * 
	@EnableConfigServer 
	@SpringBootApplication
	public class Application {
	
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	
	}
 * 
 * ##################################################################################
 * 
 * [[[Configuracion del SERVIDOR en archivo application]]]
 * 
 * > Para conexiones REMOTAS
 * 
 * Mediante la propiedad "spring.cloud.config.server.git"
 * se establece la configuracion del repositorio, usando 2 principales
 * subpropiedades:
 * 
 * 	I. uri: Direccion del repositorio remoto
 * 	II.default-label: Nombre de la rama dentro del repositorio
 * 
 * NOTA: A partir de 2021, la rama principal deja de llamarse
 * "master" y pasa a ser "main"  
 * 
 * > Para conexiones LOCALES
 * 
 * I. Los archivos de configuracion de cada microservicio se colocan dentro de un directorio 
 * del servidor Cloud Config.
 * 
 * II. Se debe indicar que el perfil a utilizar es el native (servidor local)
 * 		"spring.profiles.active = native"
 * 
 * III. Mediante la propiedad "spring:cloud:config:server:native:search-locations:file"
 * se establece la localizacion (o ruta) de los archivos en el servidor de 
 * configuracion del repositorio LOCAL, 
 * 
 * Dependiendo el SO se sigue una regla en el campo "file" en la que
 * se le deben agregar / antes de la ruta del directorio:
 * 
 * 	- 2 / para Linux
 * 	- 3 / para Windows
 * 
 * #NOTA: Esto sustituye a "spring.cloud.config.server.git.uri"
 * 
 * ##################################################################################
 * 
 * [[[Configuracion de los microservicios CLIENTE]]]
 * 
 * > Los archivos de configuracion de los servicios se llevan al repositorio, 
 * ya sea de uno remoto GitHub o local; cada uno de ellos son renombrados 
 * como "[nombre-servicio].yml"
 * 
 * > Se agrega la dependencia "Config Client" de Spring Cloud Config:
 * 
 * <dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
   </dependency>
 
 * 
 * ##################################################################################
 * 
 * [[[Configuracion del CLIENTE en el archivo application]]]
 * 
 * > En cada archivo de configuracion solo tienen que ir 2 propiedades las 
 * cuales son el nombre (spring.application.name) y la ruta de config. remota. 
 * 
 * > Dependiendo de la version de Spring Boot varia la propiedad que
 * establece la localizacion del repositorio:
 * 
 * 	- Si es <= SB 2.4: 	
 * 	spring.cloud.config.uri
 * 
 * 	- Si es > SB 2.4:
 * 	spring.cloud.config.enabled= true
 *  
 *  - Si es >= SB 2.7
 *  spring.config.import= configserver:http://[_URI_]
 *  spring.cloud.config.enabled= true
 *  
 *  ##################################################################################
 *  
 *  [[[ Nombre y Dependencias para el archivo de configuracion ]]] 
 *  
 *  - Menor a SB 2.4 -
 *  El archivo de configuracion debe llamarse "bootstrap.yml" 
 *  
 *  - De la version SB 2.4 o +. -
 *  El archivo "bootstrap.yml" no es encontrado por el microservicio, por lo que se debe
 *  agregar un starter para que lo encuentre.
 *  
	<dependency>
	  <groupId›org-springframework.cloud</groupId>
	  <artifactId›spring-cloud-starter-bootstrap</artifactId>
	</dependency>
 *  
 *  NOTA: Puede que el asistente de dependencias no lo agregue bien, por lo que se debe
 *  agregar el nombre corregido manualmente
 *  
 *  - A partir de la version 2.7 -
 *  El archivo de propiedades ya no se llama "bootstrap", sino que mantiene el nombre 
 *  original "application" (.properties o .yml, según lo tenga). 
 *  
 *  NOTA: Tampoco es necesario la dependencia "spring-cloud-starter-bootstrap" a partir
 *  de esta version, 
 *  solo en el caso en el que se quiera usar "bootstrap" en vez de "application" 
 *  en el archivo de configuracion.
 *  
 *  ##################################################################################
 *  
 *  [[[ Autenticacion y repositorios privados ]]]
 *  
 *  En el caso de que los archivos de configuracion estuvieran en un repositorio privado, 
 *  se requeriría un proceso de autenticacion desde el servidor de configuracion y, por tanto, 
 *  proporcionar las credenciales en el .yml del servidor. Por ejemplo:
 * 
	spring:
	  cloud:
	    config:
	      server:
	        git:
	          uri: https://github.com/amartinsierra/repoprivateconfig
	          username: myuser
	          password: mypass
 *  
 *  Tambien podría utilizarse otro mecanismo de autenticacion como SSH
 */

//Lo habilita como un Servidor de Configuracion
@EnableConfigServer 
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
