package br.com.trainees.pizzaria.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UsuarioAtualizaDTO(
	@JsonAlias String nome,
	@JsonAlias String telefone,
	@JsonAlias String email,
	@JsonAlias String senha
	){ }
