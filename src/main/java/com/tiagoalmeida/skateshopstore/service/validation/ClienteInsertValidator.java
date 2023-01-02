package com.tiagoalmeida.skateshopstore.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tiagoalmeida.skateshopstore.domain.enums.TipoCliente;
import com.tiagoalmeida.skateshopstore.dto.ClienteNewDTO;
import com.tiagoalmeida.skateshopstore.resources.exceptions.FieldMessage;
import com.tiagoalmeida.skateshopstore.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
