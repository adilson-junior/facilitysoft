package br.com.facilitysoft.facilitysoft.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.facilitysoft.facilitysoft.dominio.Categoria;
import br.com.facilitysoft.facilitysoft.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	 
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {     
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElse(null); 
		}

}
