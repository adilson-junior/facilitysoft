package br.com.facilitysoft.facilitysoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.facilitysoft.facilitysoft.dominio.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	

}
