package br.com.socialbooksapi.services;

import java.util.List;

import br.com.socialbooksapi.domain.Autor;

public interface AutorServiceInterface {

	public List<Autor> listarAutores();
	
	public Autor salvarAutor(Autor autor);
	
	public Autor buscarAutor(Long id);
}
