package repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Categoria;

public interface RepositorioCategoria extends JpaRepository<Categoria,Long>{
	List<Categoria> findByNombreCategoria(String nombreCategoria);
}
