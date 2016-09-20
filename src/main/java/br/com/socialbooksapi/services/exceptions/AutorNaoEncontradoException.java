package br.com.socialbooksapi.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061875452707872011L;

	public AutorNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	
	public AutorNaoEncontradoException(String mensagem, Throwable causa){
		super(mensagem,causa);
	}
}
