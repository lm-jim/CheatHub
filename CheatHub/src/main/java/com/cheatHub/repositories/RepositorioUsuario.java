package com.cheatHub.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheatHub.entities.Usuario;
@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,String>{

}
