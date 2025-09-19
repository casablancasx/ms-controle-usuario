package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.adpter.SuperSapiensAdapter;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginResponseDTO;

import static br.gov.agu.nutec.mscontroledeusuario.util.TokenUtil.decodeToken;
import static br.gov.agu.nutec.mscontroledeusuario.enums.UserRole.USER;

import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import br.gov.agu.nutec.mscontroledeusuario.entity.UsuarioEntity;
import br.gov.agu.nutec.mscontroledeusuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsuarioRepository usuarioRepository;
    private final SuperSapiensAdapter superSapiensAdapter;
    private static final String SAO_PAULO_ZONE_ID = "America/Sao_Paulo";

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        String token = superSapiensAdapter.getAuthTokenSuperSapiens(loginRequest);
        TokenDecoded tokenDecoded = decodeToken(token);
        UsuarioEntity user = buscarOuCriarUsuario(tokenDecoded);
        return new LoginResponseDTO(
                user,
                token
        );
    }

    private UsuarioEntity buscarOuCriarUsuario(TokenDecoded tokenDecoded) {
        var user = usuarioRepository.findBySapiensId(tokenDecoded.sapiensId()).orElseGet(
                () -> {
                    UsuarioEntity newUser = new UsuarioEntity();
                    newUser.setSapiensId(tokenDecoded.sapiensId());
                    newUser.setNome(tokenDecoded.nome());
                    newUser.setEmail(tokenDecoded.email());
                    newUser.setRole(USER);
                    newUser.setCriadoEm(LocalDateTime.now(
                            java.time.ZoneId.of(SAO_PAULO_ZONE_ID)
                    ));
                    return usuarioRepository.save(newUser);
                }
        );

        user.setUltimoAcesso(LocalDateTime.now(
                java.time.ZoneId.of(SAO_PAULO_ZONE_ID)
        ));

        return usuarioRepository.save(user);
    }


}
