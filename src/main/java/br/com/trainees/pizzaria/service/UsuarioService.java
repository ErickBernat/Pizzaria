package br.com.trainees.pizzaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.domain.entity.Usuario;
import br.com.trainees.pizzaria.domain.exception.IdUsuarioNaoEncontradoException;
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
		return  converteUsuarioDTO(usuario.get());
	}
	
	public List<UsuarioDTO> buscaTodosUsuarios(){
		return usuarioRepository.findAll().stream().map(this::converteUsuarioDTO).toList();
	}
	
	
	private UsuarioDTO converteUsuarioDTO(Usuario usuario) {
	    return new UsuarioDTO(
	    	usuario.getId(),
	        usuario.getCpf(),
	        usuario.getNome(),
	        usuario.getTelefone(),
	        usuario.getEmail(),
	        usuario.getSenha(),
	        usuario.getEndereco().getId()
	    );
	}
	

}
