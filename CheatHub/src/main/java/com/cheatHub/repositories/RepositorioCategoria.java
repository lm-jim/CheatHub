package com.cheatHub.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Categoria;
@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria,String>{
	
}
