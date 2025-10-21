package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.adpter.SuperSapiensAdapter;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.RefreshTokenResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import br.gov.agu.nutec.mscontroledeusuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.gov.agu.nutec.mscontroledeusuario.util.TokenUtil.decodeToken;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SuperSapiensAdapter superSapiensAdapter;
    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        String token = superSapiensAdapter.getAuthTokenSuperSapiens(loginRequest);
        TokenDecoded tokenDecoded = decodeToken(token);
        var user = usuarioService.buscarUsuario(tokenDecoded);
        var userResponse = mapper.mapToResponse(user);
        return new LoginResponseDTO(userResponse, token);
    }

    public RefreshTokenResponseDTO refreshAccessToken(String refreshToken) {
        String novoToken = superSapiensAdapter.refreshAuthTokenSuperSapiens(refreshToken);
        return new RefreshTokenResponseDTO(novoToken);
    }
}
