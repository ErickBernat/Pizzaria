package br.com.trainees.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/{cpf}")
	public ResponseEntity<UsuarioDTO> obterUsuarioPorCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(service.buscarUsuarioPorCpf(cpf));
	}
}
