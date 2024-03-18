package br.com.vianadoria.desafiojava.service;

import br.com.vianadoria.desafiojava.model.Projeto;
import br.com.vianadoria.desafiojava.repo.IProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    IProjetoRepository projetoRepo;

    public List<Projeto> obterTodosProjetos() {
        List<Projeto> listaDeProjeto = new ArrayList<>();
        projetoRepo.findAll().forEach(projeto -> listaDeProjeto.add(projeto));
        return listaDeProjeto;
    }

    public Projeto obterProjetoPorId(Long id) {
        Optional<Projeto> projeto = projetoRepo.findById(id);
        if (projeto.isPresent()) {
            return projeto.get();
        } else {
            return null;
        }
    }

    public boolean salvarOuAtualizarProjeto(Projeto projeto) {
        try {
            projetoRepo.save(projeto);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean detetarProjeto(Long id) {
        try {
            Optional<Projeto> projeto = projetoRepo.findById(id);
            if (projeto.isPresent()) {
                if (projeto.get().getStatus().equals("iniciado")
                        || projeto.get().getStatus().equals("em andamento")
                        || projeto.get().getStatus().equals("encerrado")) {
                    return false;
                } else {
                    projetoRepo.deleteById(id);
                    return true;
                }
            } else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }

}
