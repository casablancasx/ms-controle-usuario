package br.gov.agu.nutec.mscontroledeusuario.exception;

import java.time.Instant;

public record StandardError(
        Integer status,
        String message,
        String error,
        Instant timestamp,
        String path
) {
}
