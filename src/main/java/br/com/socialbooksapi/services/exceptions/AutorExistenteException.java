package br.com.socialbooksapi.services.exceptions;

public class AutorExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061875452707872011L;
	
	public AutorExistenteException(String mensagem){
		super(mensagem);
	}
	
	public AutorExistenteException(String mensagem, Throwable causa){
		super(mensagem,causa);
	}

}
