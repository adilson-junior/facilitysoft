package br.com.facilitysoft.facilitysoft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacilitysoftApplication implements CommandLineRunner {

	

	public static void main(String[] args) {
		SpringApplication.run(FacilitysoftApplication.class, args);
	}

	// metodo auxiliar para criar os objetos no banco de teste
	@Override
	public void run(String... args) throws Exception {

		
	}

}
