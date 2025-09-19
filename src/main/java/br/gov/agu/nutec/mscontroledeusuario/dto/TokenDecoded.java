package br.gov.agu.nutec.mscontroledeusuario.dto;

public class TokenDecoded {

    private String nome;
    private String cpf;
    private String email;
    private Long sapiensId;
    private Long unidadeId;
    private Long setorId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSapiensId() {
        return sapiensId;
    }

    public void setSapiensId(Long sapiensId) {
        this.sapiensId = sapiensId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public Long getSetorId() {
        return setorId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }
}
