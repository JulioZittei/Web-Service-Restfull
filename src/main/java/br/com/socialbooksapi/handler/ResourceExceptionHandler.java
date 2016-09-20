package br.com.socialbooksapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.socialbooksapi.domain.DetalhesErro;
import br.com.socialbooksapi.services.exceptions.AutorExistenteException;
import br.com.socialbooksapi.services.exceptions.AutorNaoEncontradoException;
import br.com.socialbooksapi.services.exceptions.LivroExistenteException;
import br.com.socialbooksapi.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<?> handlerLivroNaoEncontradoException(LivroNaoEncontradoException e,HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo("O Livro não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<?> handlerAutorNaoEncontradoException(AutorNaoEncontradoException e,HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo("O Autor não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(LivroExistenteException.class)
	public ResponseEntity<?> handlerLivroExistenteException(LivroExistenteException e, HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409L);
		erro.setTitulo("O Livro já existe");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<?> handlerAutorExistenteException(AutorExistenteException e, HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409L);
		erro.setTitulo("O Autor já existe");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
