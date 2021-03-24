package com.crud.CRUDSpring.repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crud.CRUDSpring.model.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Integer>{
	
	@Query("SELECT p from Profesor p where p.idProf =:idProf")
	public List<Profesor> profesores(@Param("idProf") int idProf);

}
