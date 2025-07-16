package br.com.trainees.pizzaria.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trainees.pizzaria.domain.dto.UsuarioCadastroDTO;
import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioCadastroDTO usuarioCadastroDto, HttpServletRequest httpServletRequest) {
		UsuarioDTO usuarioDto = usuarioService.cadastrarUsuario(usuarioCadastroDto);
		String location = httpServletRequest.getRequestURL().append("/" + usuarioDto.id()).toString();
		URI locationUri = URI.create(location);
		
		return ResponseEntity.created(locationUri).body(usuarioDto);
	}
}
