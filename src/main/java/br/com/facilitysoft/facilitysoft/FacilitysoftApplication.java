package br.com.facilitysoft.facilitysoft;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.facilitysoft.facilitysoft.dominio.Categoria;
import br.com.facilitysoft.facilitysoft.repositories.CategoriaRepository;

@SpringBootApplication
public class FacilitysoftApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(FacilitysoftApplication.class, args);
	}

	//metodo auxiliar para criar os objetos no banco de teste
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
