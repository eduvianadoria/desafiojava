package br.com.vianadoria.desafiojava.repo;

import br.com.vianadoria.desafiojava.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjetoRepository extends JpaRepository<Projeto, Long> {
}
