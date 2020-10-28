package com.crud.CRUDSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.crud.CRUDSpring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
	@Query("SELECT * FROM User u "
			+ "INNER JOIN authotiries_users a ON u.id = a.usuario_id"
			+ "WHERE a.authority_id = 2"
           )
	public List<User> allUsers();
	
	@Query("SELECT u FROM User u WHERE u.username LIKE %?1%"
            + " OR i.id LIKE %?1%"
            + " OR p.modelo LIKE %?1%"
            + " OR p.cantidad LIKE %?1%")
	public List<User> searchUser(String busquedaUser);
	
	public Optional<User> findByUsername(String username);

   
}