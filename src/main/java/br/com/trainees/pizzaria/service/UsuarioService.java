package br.com.trainees.pizzaria.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.trainees.pizzaria.domain.converter.UsuarioConverter;
import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.domain.entity.Usuario;
import br.com.trainees.pizzaria.domain.exception.IdUsuarioNaoEncontradoException;
import br.com.trainees.pizzaria.domain.exception.UsuarioDuplicadoException;
import br.com.trainees.pizzaria.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public UsuarioDTO buscaUsuarioId(Long id){
		Optional<Usuario> usuario = usuarioRepository.buscaUsuarioPorId(id);
		if(usuario.isEmpty()){
			throw new IdUsuarioNaoEncontradoException();
		}
		return UsuarioConverter.toDto(usuario.get());
	}
	
	public List<UsuarioDTO> buscaTodosUsuarios(){
		return usuarioRepository.findAll().stream().map(e -> UsuarioConverter.toDto(e)).toList();
	}
	
	public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDto) {
	    Usuario usuarioUpdate = usuarioRepository.buscaUsuarioPorId(id)
	        .orElseThrow(() -> new IdUsuarioNaoEncontradoException());

	    UsuarioConverter.updateUsuario(usuarioUpdate, usuarioDto);

	    validarUpdate(usuarioUpdate, id);

	    usuarioRepository.save(usuarioUpdate);

	    return UsuarioConverter.toDto(usuarioUpdate);
	}
	
	private void validarUpdate(Usuario usuarioUpdate, Long id) {
		if (usuarioRepository.existeOutroUsuarioComMesmoEmail(id, usuarioUpdate.getEmail())) {
			throw new UsuarioDuplicadoException();
		}
		if (usuarioRepository.existeOutroUsuarioComMesmoCpf(id, usuarioUpdate.getCpf())) {
			throw new UsuarioDuplicadoException();
		}
		usuarioUpdate.setId(id);
	}
	
}
