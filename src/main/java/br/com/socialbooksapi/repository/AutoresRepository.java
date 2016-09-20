package br.com.socialbooksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.socialbooksapi.domain.Autor;

@Repository
public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
