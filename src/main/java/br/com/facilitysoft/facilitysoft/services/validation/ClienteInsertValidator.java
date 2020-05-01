package br.com.facilitysoft.facilitysoft.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.facilitysoft.facilitysoft.dominio.enums.TipoCliente;
import br.com.facilitysoft.facilitysoft.dto.NewClienteDTO;
import br.com.facilitysoft.facilitysoft.resources.exception.FieldMessage;
import br.com.facilitysoft.facilitysoft.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO> {
	 @Override
	 public void initialize(ClienteInsert ann) {
	 }
	 @Override
	 public boolean isValid(NewClienteDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();

	 // inclua os testes aqui, inserindo erros na lista
	 if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

	 for (FieldMessage e : list) {
	 context.disableDefaultConstraintViolation();
	 context.buildConstraintViolationWithTemplate(e.getMessage())
	 .addPropertyNode(e.getFieldName()).addConstraintViolation();
	 }
	 return list.isEmpty();
	 }
	}
