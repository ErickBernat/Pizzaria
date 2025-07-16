package br.com.trainees.pizzaria.controller;

import java.util.List;

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
	    private UsuarioService usuarioService;

	    @GetMapping("/bairro/{bairro}")
	    public ResponseEntity<List<UsuarioDTO>> buscarPorBairro(@PathVariable String bairro) {
	        List<UsuarioDTO> usuarios = usuarioService.buscaUsuarioPorBairro(bairro);
	        return ResponseEntity.ok(usuarios);
	    }
}
