package br.com.facilitysoft.facilitysoft;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.facilitysoft.facilitysoft.dominio.Categoria;
import br.com.facilitysoft.facilitysoft.dominio.Cidade;
import br.com.facilitysoft.facilitysoft.dominio.Cliente;
import br.com.facilitysoft.facilitysoft.dominio.Endereco;
import br.com.facilitysoft.facilitysoft.dominio.Estado;
import br.com.facilitysoft.facilitysoft.dominio.Produto;
import br.com.facilitysoft.facilitysoft.dominio.enums.TipoCliente;
import br.com.facilitysoft.facilitysoft.repositories.CategoriaRepository;
import br.com.facilitysoft.facilitysoft.repositories.CidadeRepository;
import br.com.facilitysoft.facilitysoft.repositories.ClienteRepository;
import br.com.facilitysoft.facilitysoft.repositories.EnderecoRepository;
import br.com.facilitysoft.facilitysoft.repositories.EstadoRepository;
import br.com.facilitysoft.facilitysoft.repositories.ProdutoRepository;

@SpringBootApplication
public class FacilitysoftApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired 
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(FacilitysoftApplication.class, args);
	}

	//metodo auxiliar para criar os objetos no banco de teste
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//add os produtos as categorias e vice versa
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlãndia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Marioa Silva", "maria@gamil.com", "21996689897", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("22228888", "77788999"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardin", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
