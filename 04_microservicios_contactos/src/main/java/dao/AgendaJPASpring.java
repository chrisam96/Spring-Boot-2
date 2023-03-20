package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import model.Contacto;

public interface AgendaJPASpring extends JpaRepository<Contacto, Integer> {

	/* > JPARepository tiene la estructura;
	 * 		extends JpaRepository<Class_Entidad, ID_entidad>
	 * ambos siendo clases (en caso de ser datos primitivos) 
	 * 
	 * > Los métodos por defecto de JPARepository tienen la 
	 * estrucutra "findByAtrib1(Param p)", opcionalmente pueden 
	 * usarse más atributos:
	 * 		findByAtrib1AndAtrib2AndAtribN(Param p1, Param p2, Param pN)
	 *
	 */
	Contacto findByEmail(String email);


	/*Instrucciones JPQL
	 * 
	 * Para poder usar métodos personalizados implementando JPA
	 *
	 * 
	 * @Transactional: Por ser operaciones JPA tienen que estar en un
	 * contexto transaccional
	 * 
	 * @Modifying: Se agrega por ser un método de acción (Runtime)
	 * en la capa de persistencia
	 * 
	 * @Query: Define el Query a ejecutar (mediante JPQL)
	 */
	@Transactional
	@Modifying
	@Query("Delete from Contacto c where c.email=?1")
	void eliminarPorEmail(String email);
	
}
