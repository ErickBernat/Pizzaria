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
			entity.getSenha(),
			entity.getStatus(),
			EnderecoConverter.toDTO(entity.getEndereco())
		);
	}

	public static void updateUsuario(Usuario usuario, UsuarioDTO dto) {
	    usuario.setNome(dto.nome());
	    usuario.setTelefone(dto.telefone());
	    usuario.setEmail(dto.email());
	    usuario.setSenha(dto.senha());
	}

	
}
