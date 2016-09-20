package br.com.socialbooksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.socialbooksapi.domain.Livro;

@Repository
public interface LivrosRepository extends JpaRepository<Livro, Long>{

	
}
