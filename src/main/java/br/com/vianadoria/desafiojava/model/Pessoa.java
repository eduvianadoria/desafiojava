package br.com.vianadoria.desafiojava.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column(name = "datanascimento")
    private Date dataNascimento;

    @Column
    private String cpf;

    @Column
    private Boolean funcionario;

    @Column
    private Boolean gerente;
}
