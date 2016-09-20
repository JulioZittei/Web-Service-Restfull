package br.com.socialbooksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.socialbooksapi.domain.Comentario;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentario, Long>  {

}
