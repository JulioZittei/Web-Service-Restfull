package br.com.socialbooksapi.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.socialbooksapi.domain.Comentario;
import br.com.socialbooksapi.domain.Livro;
import br.com.socialbooksapi.services.LivroService;

@RestController
@RequestMapping(value = "/livros", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class LivrosResources {
	
	@Autowired
	private LivroService livroService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livroService.listarLivros());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro){
		livro = livroService.salvarLivro(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Livro> buscar(@PathVariable("id") Long id){
		Livro livro = livroService.buscarLivro(id);
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		livroService.deletarLivro(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro){
		livroService.atualizarLivro(livro);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/comentarios",method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long id, @RequestBody Comentario coment){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		coment.setUsuario(auth.getName().toString());
		livroService.salvarComentario(id, coment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}/comentarios",method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long id){
		List<Comentario> comentarios = livroService.listarComentarios(id);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
