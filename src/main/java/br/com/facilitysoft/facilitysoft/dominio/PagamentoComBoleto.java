package br.com.facilitysoft.facilitysoft.dominio;

import java.util.Date;

import javax.persistence.Entity;

import br.com.facilitysoft.facilitysoft.dominio.enums.EstadoPagamento;


@Entity
public class PagamentoComBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estato, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estato, pedido);
		this.setDataPagamento(dataPagamento);
		this.setDataVencimento(dataVencimento);
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	

}
