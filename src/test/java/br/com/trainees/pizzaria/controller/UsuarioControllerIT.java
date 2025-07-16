package br.com.trainees.pizzaria.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import br.com.trainees.pizzaria.domain.converter.UsuarioConverter;
import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import br.com.trainees.pizzaria.domain.entity.Endereco;
import br.com.trainees.pizzaria.domain.entity.Usuario;
import br.com.trainees.pizzaria.repository.EnderecoRepository;
import br.com.trainees.pizzaria.repository.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioControllerIT {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final String urlPadrao = "http://localhost:8081/usuarios";
	
	private Usuario entity;
	
	@BeforeAll
	void setup() {
		entity = cadastroUsuario(buildUsuario());
	}

	@Nested
	@DisplayName("GET /usuarios")
	class Get {
		
		@Nested
		@DisplayName("Deve retornar o status 200")
		class GetSucess {
			
			@Test
			@DisplayName("Deve retornar um usuario pelo cpf")
			void testObterUsuarioPorCpf() throws Exception {
				Integer statusCode = 200;
				Usuario usuario = entity;
				UsuarioDTO usuarioEsperadoDto = UsuarioConverter.toDto(entity);
				
				MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(urlPadrao + "/id/" + entity.getId())).andReturn().getResponse();
			
				UsuarioDTO usuarioRespostaDto = new Gson().fromJson(response.getContentAsString(), UsuarioDTO.class);
				
				Assertions.assertEquals(statusCode, response.getStatus());
				Assertions.assertEquals(usuarioEsperadoDto, usuarioRespostaDto);
			}
		}
	}
	
	private Usuario cadastroUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	private Usuario buildUsuario() {
		Endereco enderecoEntity = cadastroEndereco(buildEndereco());
		
		return new Usuario(
			"12345678911", 
			"Teste de Usuario Nome", 
			"11934455470", 
			"teste@teste.com", 
			"12345678", 
			true, 
			enderecoEntity
		);
	}
	
	private Endereco cadastroEndereco(Endereco enderecoEntity) {
		return enderecoRepository.save(enderecoEntity);
	}
	
	private Endereco buildEndereco() {
		return new Endereco("Logradouro 123", "07855090", "Bairro Teste", "Nada a declarar");
	}
}
