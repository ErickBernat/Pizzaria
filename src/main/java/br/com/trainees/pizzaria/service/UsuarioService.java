package br.com.trainees.pizzaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trainees.pizzaria.domain.dto.UsuarioCadastroDTO;
import br.com.trainees.pizzaria.domain.entity.Endereco;
import br.com.trainees.pizzaria.domain.entity.Usuario;
import br.com.trainees.pizzaria.domain.exception.EnderecoNaoEncontradoException;
import br.com.trainees.pizzaria.domain.exception.UsuarioJaExistenteException;
import br.com.trainees.pizzaria.repository.EnderecoRepository;
import br.com.trainees.pizzaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void cadastrarUsuario(UsuarioCadastroDTO usuarioCadastroDto) {
		Endereco enderecoEntity = pegarEnderecoPeloId(usuarioCadastroDto.enderecoId());
		checarCpfJaFoiCadastrado(usuarioCadastroDto.cpf());
		Usuario usuarioEntity = new Usuario(usuarioCadastroDto);
		usuarioEntity.setEndereco(enderecoEntity);
		
		usuarioRepository.save(usuarioEntity);
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
