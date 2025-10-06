package br.gov.agu.nutec.mscontroledeusuario.dto;


public record UserResponseDTO(
        Long sapiensId,
        String nome,
        String email,
        SetorResponse setor
) {
}
