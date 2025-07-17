package br.com.trainees.pizzaria.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
		@JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
		@NotBlank String cpf,
		@NotBlank String nome,
		@NotBlank String telefone,
		@NotBlank @Email String email,
		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @NotBlank String senha,
		@NotBlank boolean status,
		EnderecoDTO endereco
) {}
