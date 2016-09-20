package br.com.socialbooksapi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.socialbooksapi.domain.Comentario;
import br.com.socialbooksapi.domain.Livro;
import br.com.socialbooksapi.repository.ComentariosRepository;
import br.com.socialbooksapi.repository.LivrosRepository;
import br.com.socialbooksapi.services.exceptions.LivroExistenteException;
import br.com.socialbooksapi.services.exceptions.LivroNaoEncontradoException;


@Service
public class LivroService implements LivroServiceInterface{
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentariosRepository comentariosRepository;

	@Override
	public List<Livro> listarLivros() {
		return livrosRepository.findAll();
	}

	@Override
	public Livro salvarLivro(Livro livro) {
		Livro checkLivro = null;
		if(livro.getId() != null){
			checkLivro = livrosRepository.findOne(livro.getId());
		}
		if(checkLivro != null){
			throw new LivroExistenteException("O Livro já existe");
		}
		return livrosRepository.save(livro);
	}

	@Override
	public Livro buscarLivro(Long id) {
		Livro livro =  livrosRepository.findOne(id);
		if(livro == null){
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
		}
		return livro;
	}

	@Override
	public void deletarLivro(Long id) {
		try{
			livrosRepository.delete(id);
		}catch(EmptyResultDataAccessException e){
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
		}
	}

	@Override
	public void atualizarLivro(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}	
	
	private void verificarExistencia(Livro livro){
		buscarLivro(livro.getId());
	}

	@Override
	public Comentario salvarComentario(Long id, Comentario coment) {
		Livro livro = buscarLivro(id);
		coment.setLivro(livro);
		coment.setData(new Date());
		return comentariosRepository.save(coment);
	}

	@Override
	public List<Comentario> listarComentarios(Long id) {
		Livro livro = buscarLivro(id);
		return livro.getComentarios();
	}
	
}
