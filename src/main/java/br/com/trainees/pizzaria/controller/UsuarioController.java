package br.com.trainees.pizzaria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.trainees.pizzaria.domain.dto.UsuarioAtualizaDTO;
import br.com.trainees.pizzaria.domain.dto.UsuarioCadastroDTO;
import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
  @Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<UsuarioDTO> obterUsuarioPorCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(usuarioService.buscarUsuarioPorCpf(cpf));
	}

  
	@GetMapping("/id/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
	    UsuarioDTO usuario = usuarioService.buscaUsuarioId(id);
	    return ResponseEntity.ok(usuario);
	}

	@GetMapping("/bairro/{bairro}")
	public ResponseEntity<List<UsuarioDTO>> buscarPorBairro(@PathVariable String bairro) {
	    List<UsuarioDTO> usuarios = usuarioService.buscaUsuarioPorBairro(bairro);
	    return ResponseEntity.ok(usuarios);
	}
  
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioCadastroDTO usuarioCadastroDto, HttpServletRequest httpServletRequest) {
		UsuarioDTO usuarioDto = usuarioService.cadastrarUsuario(usuarioCadastroDto);
		String location = httpServletRequest.getRequestURL().append("/" + usuarioDto.id()).toString();
		URI locationUri = URI.create(location);
		
		return ResponseEntity.created(locationUri).body(usuarioDto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> atualizaUsuario(@PathVariable Long id , @RequestBody UsuarioAtualizaDTO usuarioAtualizaDTO){
		UsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioAtualizaDTO);
		return ResponseEntity.ok(usuarioAtualizado);

	@PatchMapping("/{id}/inativar")
	public ResponseEntity<?> inativarUsuario(@PathVariable("id") Long usuarioId) {
		usuarioService.deletarUsuario(usuarioId);
		return ResponseEntity.noContent().build();

	}
	
	@GetMapping("/todosUsuarios")
    public ResponseEntity<List<UsuarioDTO>> buscarTodosOsUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.buscaTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
