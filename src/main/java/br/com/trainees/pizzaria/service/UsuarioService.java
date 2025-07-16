package br.com.trainees.pizzaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trainees.pizzaria.domain.converter.UsuarioConverter;
import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.domain.entity.Usuario;
import br.com.trainees.pizzaria.domain.exception.IdUsuarioNaoEncontradoException;
import br.com.trainees.pizzaria.domain.exception.UsuarioNaoEncontradoException;
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
		return  UsuarioConverter.toDto(usuario.get());
	}
	
	public List<UsuarioDTO> buscaTodosUsuarios(){
		return usuarioRepository.findAll().stream().map(UsuarioConverter::toDto).toList();
	}
	public UsuarioDTO buscaUsuarioPorEmail(String email) {
	    Optional<Usuario> usuario = usuarioRepository.buscaUsuarioPorEmail(email);
	    if (usuario.isEmpty()) {
	        throw new UsuarioNaoEncontradoException();
	    }
	    return UsuarioConverter.toDto(usuario.get());
	}
  
	public void deletarUsuario(Long id) {
		boolean usuarioExiste = usuarioRepository.existsById(id);
		if(!usuarioExiste) throw new IdUsuarioNaoEncontradoException();
		
		usuarioRepository.deixaUsuarioInativo(id);
	}
}
