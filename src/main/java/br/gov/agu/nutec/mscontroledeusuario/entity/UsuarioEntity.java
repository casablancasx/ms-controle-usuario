package br.gov.agu.nutec.mscontroledeusuario.entity;

import br.gov.agu.nutec.mscontroledeusuario.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    private String nome;

    private String email;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "setor_id")
    private Long setorId;

    @Column(name = "unidade_id")
    private Long unidadeId;

    @Column(name = "sapiens_id", unique = true)
    private Long sapiensId;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "ultimo_acesso")

    private LocalDateTime ultimoAcesso;
}
