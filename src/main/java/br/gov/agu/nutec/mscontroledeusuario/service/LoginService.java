package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.adpter.SuperSapiensAdapter;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import br.gov.agu.nutec.mscontroledeusuario.dto.UserResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.entity.UsuarioEntity;
import br.gov.agu.nutec.mscontroledeusuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.gov.agu.nutec.mscontroledeusuario.util.TokenUtil.decodeToken;

@Service
@RequiredArgsConstructor
public class LoginService {

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
}
