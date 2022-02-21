package com.cheatHub.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Categoria;
@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria,String>{
	List<Categoria> findByNombreCategoria(String nombreCategoria);
}
