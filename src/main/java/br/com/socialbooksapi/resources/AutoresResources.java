package br.com.socialbooksapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.socialbooksapi.domain.Autor;
import br.com.socialbooksapi.services.AutorService;

@RestController
@RequestMapping("/autores")
public class AutoresResources {
	
	@Autowired
	private AutorService autorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listarAutores(){
		return ResponseEntity.status(HttpStatus.OK).body(autorService.listarAutores());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarAutores(@Valid @RequestBody Autor autor){
		autor = autorService.salvarAutor(autor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscarAutor(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(autorService.buscarAutor(id));
	}

}
