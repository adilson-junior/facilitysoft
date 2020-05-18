package br.com.facilitysoft.facilitysoft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.facilitysoft.facilitysoft.dominio.Cidade;
import br.com.facilitysoft.facilitysoft.dominio.Cliente;
import br.com.facilitysoft.facilitysoft.dominio.Endereco;
import br.com.facilitysoft.facilitysoft.dominio.enums.Perfil;
import br.com.facilitysoft.facilitysoft.dominio.enums.TipoCliente;
import br.com.facilitysoft.facilitysoft.dto.ClienteDTO;
import br.com.facilitysoft.facilitysoft.dto.NewClienteDTO;
import br.com.facilitysoft.facilitysoft.repositories.ClienteRepository;
import br.com.facilitysoft.facilitysoft.repositories.EnderecoRepository;
import br.com.facilitysoft.facilitysoft.security.UserSS;
import br.com.facilitysoft.facilitysoft.services.exceptions.AuthorizationException;
import br.com.facilitysoft.facilitysoft.services.exceptions.DataIntegrityException;
import br.com.facilitysoft.facilitysoft.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		enderecoRepository.saveAll(obj.getEnderecos());
		obj = repo.save(obj);
		
		return obj;
	}


	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cliente com pedidos relacionados.");
		}
			
	}

	public List<Cliente> findAll() {		
		return repo.findAll();
	}
	 
	//metodo que faz a paginação da lista
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {	
		PageRequest pageRequest= PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//metodo auxiliar para instanciar uma Cliente apartir de um DTO
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	//metodo auxiliar para instanciar uma Cliente apartir de um DTO
	public Cliente fromDTO(NewClienteDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()) );
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLougradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
}
