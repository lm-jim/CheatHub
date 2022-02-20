package com.cheatHub.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Usuario;
@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,String>{
	List<Usuario> findByNombreUsuario(String nombreUsuario);
}
