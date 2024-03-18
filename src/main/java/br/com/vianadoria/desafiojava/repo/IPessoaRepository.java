package br.com.vianadoria.desafiojava.repo;

import br.com.vianadoria.desafiojava.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("from Pessoa P WHERE P.gerente = true"
    )
    List<Pessoa> listarGerentes();

    @Query("from Pessoa P WHERE P.funcionario = true"
    )
    List<Pessoa> listarFuncionarios();
}
