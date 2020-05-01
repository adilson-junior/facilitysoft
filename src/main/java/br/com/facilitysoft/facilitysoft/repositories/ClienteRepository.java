package br.com.facilitysoft.facilitysoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.facilitysoft.facilitysoft.dominio.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly = true)
	Cliente findByCpfOuCnpj(String cpfOuCnpj);

}
