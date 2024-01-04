	package eduv.tecazuay.tec.repository;
	
	
	import java.util.Optional;
	
	import org.springframework.stereotype.Repository;
	
	import eduv.tecazuay.tec.entity.Cancion;
	
	@Repository
	public interface ICancionRepository extends IGenericRepository<Cancion>{
	
		/*@Query(value = "SELECT c FROM Cancion c WHERE c.title like %:name%", nativeQuery = true)
	    Cancion findByName(@Param("name") String name);*/
		
		Optional<Cancion> findByTitle(String title); 
	}
