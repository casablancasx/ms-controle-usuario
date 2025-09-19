package br.gov.agu.nutec.mscontroledeusuario.dto;

public record LoginRequestDTO(
        String email,
        String password
) {
}
