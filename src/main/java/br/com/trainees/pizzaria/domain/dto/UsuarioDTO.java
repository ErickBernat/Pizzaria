package br.com.trainees.pizzaria.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
		@JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
		@NotBlank String cpf,
		@NotBlank String nome,
		@NotBlank String telefone,
		@NotBlank @Email String email,
		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @NotBlank String senha,
		@NotNull Long enderecoId,
		@NotBlank boolean ativo)
		
{
}
