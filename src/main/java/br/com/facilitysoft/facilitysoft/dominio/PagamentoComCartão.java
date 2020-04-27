package br.com.facilitysoft.facilitysoft.dominio;

import javax.persistence.Entity;

import br.com.facilitysoft.facilitysoft.dominio.enums.EstadoPagamento;

@Entity
public class PagamentoComCartão extends Pagamento {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartão() {		
	}

	public PagamentoComCartão(Integer id, EstadoPagamento estato, Pedido pedido, Integer numeroDeParcelas) {
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
