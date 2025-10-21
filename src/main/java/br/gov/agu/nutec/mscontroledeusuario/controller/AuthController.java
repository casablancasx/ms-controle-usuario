package br.gov.agu.nutec.mscontroledeusuario.controller;

import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.RefreshTokenResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh_token")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        RefreshTokenResponseDTO response = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(response);
    }
}
