package br.gov.agu.nutec.mscontroledeusuario.dto;


public record LoginResponseDTO(
        UserResponseDTO user,
        String token
) {
}
