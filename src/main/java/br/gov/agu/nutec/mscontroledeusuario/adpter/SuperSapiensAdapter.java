package br.gov.agu.nutec.mscontroledeusuario.adpter;

import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SuperSapiensAdapter {


    private final WebClient webClient;


    public String getAuthTokenSuperSapiens(LoginRequestDTO dadosLogin) {

        var body = Map.of("username", dadosLogin.email(), "password", dadosLogin.password());

        JsonNode json = webClient.post()
                .uri("/auth/ldap_get_token")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();


        assert json != null;
        return json.get("token").asText();
    }

}
