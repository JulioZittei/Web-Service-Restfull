package br.com.socialbooksapi.services.exceptions;

public class LivroExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5333433160564281237L;
	
	public LivroExistenteException(String mensagem){
		super(mensagem);
	}
	
	public LivroExistenteException(String mensagem, Throwable causa){
		super(mensagem);
	}

}
