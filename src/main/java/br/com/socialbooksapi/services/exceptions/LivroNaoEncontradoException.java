package br.com.socialbooksapi.services.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6818282715334091096L;

	public LivroNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	
	public LivroNaoEncontradoException(String mensagem, Throwable causa){
		super(mensagem,causa);
	}
}
