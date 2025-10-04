package br.gov.agu.nutec.mscontroledeusuario.adpter;

import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.exception.AuthenticationException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SuperSapiensAdapter {

    private final WebClient webClient;


    public String getAuthTokenSuperSapiens(LoginRequestDTO dadosLogin) {

        var body = Map.of("username", dadosLogin.email(), "password", dadosLogin.password());

        ResponseEntity<JsonNode> response = webClient.post()
                .uri("/auth/ldap_get_token")
                .bodyValue(body)
                .retrieve()
                .toEntity(JsonNode.class)
                .doOnError(e -> {
                    throw new AuthenticationException("Erro ao obter token de autenticação do SuperSapiens: " + e.getMessage());
                })
                .block();


        assert response != null;
        return response.getBody().get("token").asText();
    }


    public String getNomeSetor(Long setorId, String token){
        var request = webClient.get()
                .uri("/v1/administrativo/setor/" + setorId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        return request.get("nome").asText();
    }

    public String getNomeUnidade(Long unidadeId, String token){
        var request = webClient.get()
                .uri("/v1/administrativo/unidade/" + unidadeId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        return request.get("nome").asText();
    }





}
