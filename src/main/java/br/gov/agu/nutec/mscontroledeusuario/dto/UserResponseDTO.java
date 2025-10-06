package br.gov.agu.nutec.mscontroledeusuario.dto;


import br.gov.agu.nutec.mscontroledeusuario.enums.UserRole;

public record UserResponseDTO(
        Long sapiensId,
        String nome,
        String email,
        SetorResponse setor,
        UserRole role
) {
}
