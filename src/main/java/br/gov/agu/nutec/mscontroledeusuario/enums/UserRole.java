package br.gov.agu.nutec.mscontroledeusuario.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String descricao;

    UserRole(String descricao) {
        this.descricao = descricao;
    }

}
