package br.gov.agu.nutec.mscontroledeusuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDTO(
        @JsonProperty("usuario_id")
        Long usuarioId,
        String nome,
        String email,
        SetorResponse setor
) {
}
