package br.com.vianadoria.desafiojava.service;

import br.com.vianadoria.desafiojava.model.Pessoa;
import br.com.vianadoria.desafiojava.repo.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    IPessoaRepository pessoaRepo;

    public List<Pessoa> obterPessoas(boolean gerente) {
        List<Pessoa> listaDePessoas = new ArrayList<>();
        if (gerente == true)
            pessoaRepo.listarGerentes().forEach(pessoa -> listaDePessoas.add(pessoa));
            else
                pessoaRepo.listarFuncionarios().forEach(pessoa -> listaDePessoas.add(pessoa));

        return listaDePessoas;
    }

    public Pessoa obterPessoaPorId(Long id) {
        Optional<Pessoa> pessoa = pessoaRepo.findById(id);
        if (pessoa.isPresent()) {
            return pessoa.get();
        } else {
            return null;
        }
    }
}
