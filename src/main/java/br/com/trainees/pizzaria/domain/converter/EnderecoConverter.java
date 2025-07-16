package br.com.trainees.pizzaria.domain.converter;

import br.com.trainees.pizzaria.domain.dto.EnderecoDTO;
import br.com.trainees.pizzaria.domain.entity.Endereco;

public class EnderecoConverter {
	public static EnderecoDTO toDTO(Endereco entity) {
		return new EnderecoDTO(
				entity.getId(),
				entity.getLogradouro(),
				entity.getCep(),
				entity.getBairro(),
				entity.getComplemento()
				);
	}
	
}
