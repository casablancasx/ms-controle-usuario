package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.adpter.SuperSapiensAdapter;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.RefreshTokenResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import br.gov.agu.nutec.mscontroledeusuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final SuperSapiensAdapter superSapiensAdapter;
    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;
    private final TokenService tokenService;

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        String token = superSapiensAdapter.getAuthTokenSuperSapiens(loginRequest);
        TokenDecoded tokenDecoded = tokenService.decodeToken(token);
        var user = usuarioService.buscarUsuario(tokenDecoded);
        tokenService.salvarToken(tokenDecoded.email(), token);
        var userResponse = mapper.mapToResponse(user);
        return new LoginResponseDTO(userResponse, token);
    }

    public RefreshTokenResponseDTO refreshAccessToken(String refreshToken) {
        String novoToken = superSapiensAdapter.refreshAuthTokenSuperSapiens(refreshToken);
        TokenDecoded tokenDecoded = tokenService.decodeToken(novoToken);
        tokenService.salvarToken(tokenDecoded.email(), novoToken);
        return new RefreshTokenResponseDTO(novoToken);
    }
}
