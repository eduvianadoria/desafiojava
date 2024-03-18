package br.com.vianadoria.desafiojava.controller;

import br.com.vianadoria.desafiojava.model.Membros;
import br.com.vianadoria.desafiojava.model.MembrosPK;
import br.com.vianadoria.desafiojava.model.Pessoa;
import br.com.vianadoria.desafiojava.model.Projeto;
import br.com.vianadoria.desafiojava.service.PessoaService;
import br.com.vianadoria.desafiojava.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProjetoController {

    @Autowired
    ProjetoService projetoService;

    @Autowired
    PessoaService pessoaService;

    @GetMapping({"/", "/listarProjetos"})
    public String listarProjetos(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("listaDeProjetos", projetoService.obterTodosProjetos());
        model.addAttribute("message", message);

        return "ListaProjetos";
    }

    @GetMapping("/novoProjeto")
    public String novoProjeto(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("projeto", new Projeto());
        List<String> listaStatus = new ArrayList<>();
        listaStatus.add("em análise");
        listaStatus.add("análise realizada");
        listaStatus.add("análise aprovada");
        listaStatus.add("iniciado");
        listaStatus.add("planejado");
        listaStatus.add("em andamento");
        listaStatus.add("encerrado");
        listaStatus.add("cancelado");
        model.addAttribute("listaStatus", listaStatus);
        model.addAttribute("listaDeGerentes", pessoaService.obterPessoas(true));
        model.addAttribute("message", message);

        return "NovoProjeto";
    }

    @PostMapping("/salvarProjeto")
    public String salvarProjeto(Projeto projeto, RedirectAttributes redirectAttributes) {
        projeto.setRisco("baixo risco");
        if (projetoService.salvarOuAtualizarProjeto(projeto)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/listarProjetos";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/novoProjeto";
    }

    @GetMapping("/editarProjeto/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        model.addAttribute("projeto", projetoService.obterProjetoPorId(id));

        List<String> listaStatus = new ArrayList<>();
        listaStatus.add("em análise");
        listaStatus.add("análise realizada");
        listaStatus.add("análise aprovada");
        listaStatus.add("iniciado");
        listaStatus.add("planejado");
        listaStatus.add("em andamento");
        listaStatus.add("encerrado");
        listaStatus.add("cancelado");
        model.addAttribute("listaStatus", listaStatus);

        List<String> listaRisco = new ArrayList<>();
        listaRisco.add("baixo risco");
        listaRisco.add("médio risco");
        listaRisco.add("alto risco");
        model.addAttribute("listaRisco", listaRisco);

        model.addAttribute("listaDeGerentes", pessoaService.obterPessoas(true));

        return "EditaProjeto";
    }

    @GetMapping("/membrosProjeto/{id}")
    public String membrosProjeto(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.obterProjetoPorId(id);
        model.addAttribute("projeto", projeto);
        model.addAttribute("membrosSelecionados", new ArrayList<Long>());

        List<Membros> membros = projeto.getMembros();
        List<Long> membrosPessoaId = membros.stream().map(m -> m.getPessoa().getId()).collect(Collectors.toList());

        List<Pessoa> pessoas = pessoaService.obterPessoas(false);
        List<Pessoa> pessoasDisponiveis = pessoas.stream().filter(p -> {
            if (!membrosPessoaId.contains(p.getId()))
                return true;
            else
                return false;
        }).collect(Collectors.toList());

        model.addAttribute("listaDePessoas", pessoasDisponiveis);
        model.addAttribute("listaDeMembros", membros);

        return "MembrosProjeto";
    }

    @PostMapping(value = "/salvarMembrosProjeto")
    public String salvarMembrosProjeto(String membrosSelecionados, Long idProjeto, RedirectAttributes redirectAttributes) {
        List<Long> membrosPessoaId = Arrays.stream(membrosSelecionados.split(","))
                .toList().stream().map(v -> Long.parseLong(v)).collect(Collectors.toList());

        Projeto projeto = projetoService.obterProjetoPorId(idProjeto);
        projeto.getMembros().clear();
        membrosPessoaId.forEach(m -> {
            Pessoa pessoa = pessoaService.obterPessoaPorId(m);
            if (pessoa != null) {
                Membros membro = Membros.builder()
                        .id(new MembrosPK(projeto.getId(), m))
                        .pessoa(pessoa)
                        .projeto(projeto)
                        .build();
                projeto.getMembros().add(membro);
            }
        });
        projetoService.salvarOuAtualizarProjeto(projeto);

        return "redirect:/listarProjetos";
    }

    @PostMapping("/salvarEdicaoProjeto")
    public String salvarEdicaoProjeto(Projeto projeto, RedirectAttributes redirectAttributes) {
        if (projetoService.salvarOuAtualizarProjeto(projeto)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/listarProjetos";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editarProjeto/" + projeto.getId();
    }

    @GetMapping("/deletarProjeto/{id}")
    public String deletarProjeto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (projetoService.detetarProjeto(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Delete Failure");
        }

        return "redirect:/listarProjetos";
    }

}
