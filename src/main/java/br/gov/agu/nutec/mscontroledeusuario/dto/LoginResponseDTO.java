package br.gov.agu.nutec.mscontroledeusuario.dto;

import br.gov.agu.nutec.mscontroledeusuario.entity.UsuarioEntity;

public record LoginResponseDTO(
        UsuarioEntity user,
        String token
) {
}
