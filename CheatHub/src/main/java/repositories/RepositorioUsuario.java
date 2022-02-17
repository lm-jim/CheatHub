package repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario,String>{
	List<Usuario> findByNombreUsuario(String nombreUsuario);
}
