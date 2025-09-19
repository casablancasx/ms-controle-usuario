package br.gov.agu.nutec.mscontroledeusuario.controller;

import br.gov.agu.nutec.mscontroledeusuario.dto.LoginRequestDTO;
import br.gov.agu.nutec.mscontroledeusuario.dto.LoginResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = loginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
