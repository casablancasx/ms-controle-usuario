package br.gov.agu.nutec.mscontroledeusuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_setores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorEntity {

    @Id
    @Column(name = "setor_id")
    private Long setorId;

    private String nome;


    @OneToMany(mappedBy = "setor")
    private Set<UsuarioEntity> usuarios;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private UnidadeEntity unidade;



    public SetorEntity(Long setorId, String nome, UnidadeEntity unidade) {
        this.setorId = setorId;
        this.nome = nome;
        this.unidade = unidade;
    }
}
