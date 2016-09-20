package br.com.socialbooksapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socialbooksapi.domain.Autor;
import br.com.socialbooksapi.repository.AutoresRepository;
import br.com.socialbooksapi.services.exceptions.AutorExistenteException;
import br.com.socialbooksapi.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutorService implements AutorServiceInterface {

	@Autowired
	private AutoresRepository autoresRepository;
	
	@Override
	public List<Autor> listarAutores() {
		return autoresRepository.findAll();
	}

	@Override
	public Autor salvarAutor(Autor autor) {
		Autor checkAutor = null;
		if(autor.getId() != null){
			checkAutor = autoresRepository.findOne(autor.getId());
		}
		if(checkAutor != null){
			throw new AutorExistenteException("O Autor já existe");
		}
		return autoresRepository.save(autor);
	}

	@Override
	public Autor buscarAutor(Long id){
		Autor autor = autoresRepository.findOne(id);
		if(autor == null){
			throw new AutorNaoEncontradoException("O Autor não pôde ser encontrado");
		}
		return autor;
	}

}
