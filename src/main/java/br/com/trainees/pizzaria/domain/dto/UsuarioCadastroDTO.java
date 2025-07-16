package br.com.trainees.pizzaria.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UsuarioCadastroDTO(
		@JsonAlias String cpf,
		@JsonAlias String nome,
		@JsonAlias String telefone,
		@JsonAlias String email,
		@JsonAlias String senha,
		@JsonAlias Long enderecoId
) {}
