package br.com.trainees.pizzaria.domain.dto;

import org.springframework.http.HttpStatus;

public record RespostaErroDTO(
	String mensagem,
	HttpStatus httpStatus
) {}
