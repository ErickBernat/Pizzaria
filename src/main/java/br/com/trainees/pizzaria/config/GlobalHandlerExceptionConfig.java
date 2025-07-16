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
import br.com.trainees.pizzaria.domain.exception.CpfUsuarioNaoEncontradoException;
import br.com.trainees.pizzaria.domain.exception.EnderecoNaoEncontradoException;
import br.com.trainees.pizzaria.domain.exception.UsuarioJaExistenteException;
import br.com.trainees.pizzaria.domain.exception.IdUsuarioNaoEncontradoException;

@RestControllerAdvice
public class GlobalHandlerExceptionConfig {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> manipularException(Exception ex) {
		String mensagem = verificarMensagemDaException(ex.getMessage(), "ERRO_INTERNO_SERVIDOR");
		return pegarRespostaErro(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EnderecoNaoEncontradoException.class)
	public ResponseEntity<Object> manipularEnderecoNaoEncontradoException(EnderecoNaoEncontradoException ex) {
		String mensagem = verificarMensagemDaException(ex.getMessage(), "ENDERECO_NAO_ENCONTRADO");
		return pegarRespostaErro(mensagem, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsuarioJaExistenteException.class)
	public ResponseEntity<Object> manipularUsuarioNaoEncontradoException(UsuarioJaExistenteException ex) {
		String mensagem = verificarMensagemDaException(ex.getMessage(), "USUARIO_JA_EXISTENTE");
		return pegarRespostaErro(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IdUsuarioNaoEncontradoException.class)
	public ResponseEntity<Object> manipularExceptionUsuarioId(Exception ex) {
		String mensagem = verificarMensagemDaException(ex.getMessage(), "ERRO_BUSCAR_ID_USUARIO");
		return pegarRespostaErro(mensagem, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CpfUsuarioNaoEncontradoException.class)
	public ResponseEntity<Object> manipularExceptionUsuarioCpf(CpfUsuarioNaoEncontradoException ex) {
		String mensagem = verificarMensagemDaException(ex.getMessage(), "ERRO_BUSCAR_CPF_USUARIO");
		return pegarRespostaErro(mensagem, HttpStatus.NOT_FOUND);
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
		return new ResponseEntity<>(corpoDaResposta, httpStatus); 
	}
}
