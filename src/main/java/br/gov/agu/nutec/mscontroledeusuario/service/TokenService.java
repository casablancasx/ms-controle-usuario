package br.gov.agu.nutec.mscontroledeusuario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {


    private final RedisTemplate<String, String> redisTemplate;


    public void salvarToken(String username, String token) {
        redisTemplate.opsForValue().set(username, token);
    }

}
