package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {


    private final RedisTemplate<String, String> redisTemplate;


    public void salvarToken(String username, String token) {
        redisTemplate.opsForValue().set(username, token);
    }

    public TokenDecoded decodeToken(String token) {
        DecodedJWT jwt = JWT.decode(token);


        List<String> roles = jwt.getClaim("roles").asList(String.class);

        Long setorId = null;
        Long unidadeId = null;
        for (String role : roles) {
            if (role.startsWith("ACL_SETOR_")) {
                setorId = Long.parseLong(role.substring("ACL_SETOR_" .length()));
            }
            if (role.startsWith("ACL_UNIDADE_")) {
                unidadeId = Long.parseLong(role.substring("ACL_UNIDADE_" .length()));
            }
        }

        return new TokenDecoded(
                jwt.getClaim("nome").asString(),
                jwt.getClaim("username").asString(),
                jwt.getClaim("email").asString(),
                jwt.getClaim("id").asLong(),
                unidadeId,
                setorId,
                token);
    }

}
