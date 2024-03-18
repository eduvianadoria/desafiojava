package br.com.vianadoria.desafiojava.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "membros")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Membros implements Serializable {

    @EmbeddedId
    private MembrosPK id;

    @ManyToOne
    @JoinColumn(name = "idprojeto", insertable = false, updatable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "idpessoa", insertable = false, updatable = false)
    private Pessoa pessoa;
}
