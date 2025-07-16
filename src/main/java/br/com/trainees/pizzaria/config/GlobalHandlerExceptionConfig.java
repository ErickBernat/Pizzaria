package br.com.trainees.pizzaria.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.trainees.pizzaria.domain.dto.RespostaErroDTO;

@RestControllerAdvice
public class GlobalHandlerExceptionConfig {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> manipular_Exception(Exception ex) {
		String mensagem = verificarMensagemDaException(ex.getMessage(), "ERRO_INTERNO_SERVIDOR");
		return pegarRespostaErro(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private String verificarMensagemDaException(String mensagem, String mensagemPadrao) {
		return mensagem == null ? procurarMensagemPadrao(mensagemPadrao) : mensagem;
	}
	
	private String procurarMensagemPadrao(String code) {
		MessageSourceResolvable messagemResolvable = new DefaultMessageSourceResolvable(code);
		return messageSource.getMessage(messagemResolvable, Locale.ENGLISH);
	}
	
	private ResponseEntity<Object> pegarRespostaErro(String mensagem, HttpStatus httpStatus) {
		RespostaErroDTO corpoDaResposta = new RespostaErroDTO(mensagem, httpStatus);
		return new ResponseEntity<Object>(corpoDaResposta, httpStatus); 
	}
}
