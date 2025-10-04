package br.gov.agu.nutec.mscontroledeusuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_unidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeEntity {

    @Id
    @Column(name = "unidade_id")
    private Long unidadeId;

    private String nome;

    @OneToMany(mappedBy = "unidade")
    private Set<SetorEntity> setores;



    public UnidadeEntity(Long unidadeId, String nome) {
        this.unidadeId = unidadeId;
        this.nome = nome;
    }
}
