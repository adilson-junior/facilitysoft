package br.com.facilitysoft.facilitysoft.dominio;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.facilitysoft.facilitysoft.dominio.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")	
public class PagamentoComCartao extends Pagamento {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estato, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estato, pedido);
		this.setNumeroDeParcelas(numeroDeParcelas);
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}
