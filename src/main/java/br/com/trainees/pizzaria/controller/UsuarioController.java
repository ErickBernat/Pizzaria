package br.com.trainees.pizzaria.controller;

import java.util.List;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.trainees.pizzaria.domain.dto.UsuarioCadastroDTO;
import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	 @Autowired
	    private UsuarioService usuarioService;
  
	 
	 @GetMapping("/{id}")
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
}
