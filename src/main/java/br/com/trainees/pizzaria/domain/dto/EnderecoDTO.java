package br.com.trainees.pizzaria.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
		@JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
		@NotBlank String cep,
		String bairo,
		String complemento) {
}
