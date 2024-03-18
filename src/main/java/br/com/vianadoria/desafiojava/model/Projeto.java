package br.com.vianadoria.desafiojava.model;

import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="projeto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_previsao_fim")
    @Temporal(TemporalType.DATE)
    private Date dataPrevisaoFim;

    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Column
    private String descricao;

    @Column
    private String status;

    @Column
    private Double orcamento;

    @Column
    private String risco;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;

    @OneToMany(mappedBy="projeto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membros> membros;
}
