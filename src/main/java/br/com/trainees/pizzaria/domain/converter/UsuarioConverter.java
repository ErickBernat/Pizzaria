package br.com.trainees.pizzaria.domain.converter;

import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.domain.entity.Usuario;

public class UsuarioConverter {

	public static UsuarioDTO toDto(Usuario entity) {
		return new UsuarioDTO(
				entity.getId(),
				entity.getCpf(),
				entity.getNome(),
				entity.getTelefone(),
				entity.getEmail(),
				null,
				entity.getEndereco() != null ? entity.getEndereco().getId() : null,
				entity.getAtivo()
				);
	}
}
