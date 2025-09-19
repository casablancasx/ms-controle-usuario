package br.gov.agu.nutec.mscontroledeusuario.util;

import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.List;

public class TokenUtil {


    public static TokenDecoded decodeToken(String token) {
        DecodedJWT jwt = JWT.decode(token);

        TokenDecoded tokenDecoded = new TokenDecoded();
        tokenDecoded.setSapiensId(jwt.getClaim("id").asLong());
        tokenDecoded.setNome(jwt.getClaim("nome").asString());
        tokenDecoded.setCpf(jwt.getClaim("username").asString());
        tokenDecoded.setEmail(jwt.getClaim("email").asString());

        List<String> roles = jwt.getClaim("roles").asList(String.class);

        for (String role : roles) {
            if (role.startsWith("ACL_SETOR_")) {
                long setorId = Long.parseLong(role.substring("ACL_SETOR_" .length()));
                tokenDecoded.setSetorId(setorId);

            }
            if (role.startsWith("ACL_UNIDADE_")) {
                long unidadeId = Long.parseLong(role.substring("ACL_UNIDADE_" .length()));
                tokenDecoded.setUnidadeId(unidadeId);
            }
        }

        return tokenDecoded;
    }
}
