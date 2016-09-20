package br.com.socialbooksapi.services;

import java.util.List;

import br.com.socialbooksapi.domain.Comentario;
import br.com.socialbooksapi.domain.Livro;

public interface LivroServiceInterface {

	public List<Livro> listarLivros();
	
	public Livro salvarLivro(Livro livro);
	
	public Livro buscarLivro(Long id);
	
	public void deletarLivro(Long id);
	
	public void atualizarLivro(Livro livro);
	
	public Comentario salvarComentario(Long id, Comentario coment);
	
	public List<Comentario> listarComentarios(Long id);
}
