package br.com.vianadoria.desafiojava.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(of = {"idprojeto", "idpessoa"})
public class MembrosPK  implements Serializable {
    @Column(name="idprojeto", insertable = false, updatable = false)
    private Long idProjeto;

    @Column(name="idpessoa", insertable = false, updatable = false)
    private Long idPessoa;
}
