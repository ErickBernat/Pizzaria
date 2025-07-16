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
import br.com.trainees.pizzaria.domain.dto.UsuarioCadastroDTO;
import br.com.trainees.pizzaria.domain.entity.Endereco;
import br.com.trainees.pizzaria.domain.exception.EnderecoNaoEncontradoException;
import br.com.trainees.pizzaria.domain.exception.UsuarioJaExistenteException;
import br.com.trainees.pizzaria.repository.EnderecoRepository;
import br.com.trainees.pizzaria.domain.exception.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public UsuarioDTO buscaUsuarioId(Long id){
		Optional<Usuario> usuario = usuarioRepository.buscaUsuarioPorId(id);
		if(usuario.isEmpty()){
			throw new IdUsuarioNaoEncontradoException();
		}
		return UsuarioConverter.toDto(usuario.get());
	}
	
	public List<UsuarioDTO> buscaTodosUsuarios(){
		return usuarioRepository.findAll().stream().map(UsuarioConverter::toDto).toList();
	}
	
	public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDto) {
	    Usuario usuarioUpdate = usuarioRepository.buscaUsuarioPorId(id)
	        .orElseThrow(IdUsuarioNaoEncontradoException::new);

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
	
	
	public UsuarioDTO cadastrarUsuario(UsuarioCadastroDTO usuarioCadastroDto) {
		Endereco enderecoEntity = pegarEnderecoPeloId(usuarioCadastroDto.enderecoId());
		checarCpfJaFoiCadastrado(usuarioCadastroDto.cpf());
		Usuario usuarioEntity = new Usuario(usuarioCadastroDto);
		usuarioEntity.setEndereco(enderecoEntity);
		
		return UsuarioConverter.toDto(usuarioRepository.save(usuarioEntity));
	}
	
	
	public UsuarioDTO buscaUsuarioPorEmail(String email) {
	    Optional<Usuario> usuario = usuarioRepository.buscaUsuarioPorEmail(email);
	    if (usuario.isEmpty()) {
	        throw new UsuarioNaoEncontradoException();
	    }
	    return UsuarioConverter.toDto(usuario.get());
	}
	
	public List<UsuarioDTO> buscaUsuarioPorBairro(String bairro) {
		List<Usuario> usuario = usuarioRepository.buscaUsuarioPorBairro(bairro);
		return usuario.stream()
                .map(UsuarioConverter::toDto)
                .toList();
	}
	
	public void deletarUsuario(Long id) {
		boolean usuarioExiste = usuarioRepository.existsById(id);
		if(!usuarioExiste) throw new IdUsuarioNaoEncontradoException();
		
		usuarioRepository.deixaUsuarioInativo(id);
	}
	
	private Endereco pegarEnderecoPeloId(Long enderecoId) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findById(enderecoId);
		if (enderecoOptional.isEmpty()) throw new EnderecoNaoEncontradoException();
		return enderecoOptional.get();
	}
	
	private void checarCpfJaFoiCadastrado(String cpf) {
		if (usuarioRepository.buscaUsuarioPorCpf(cpf).isPresent()) throw new UsuarioJaExistenteException();
	}
}
