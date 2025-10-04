package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.dto.TokenDecoded;
import br.gov.agu.nutec.mscontroledeusuario.entity.UsuarioEntity;
import br.gov.agu.nutec.mscontroledeusuario.enums.UserRole;
import br.gov.agu.nutec.mscontroledeusuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final SetorService setorService;
    private static final String SAO_PAULO_ZONE_ID = "America/Sao_Paulo";



    public UsuarioEntity buscarUsuario(TokenDecoded tokenDecoded) {

        Optional<UsuarioEntity> usuario = usuarioRepository.findBySapiensId(tokenDecoded.sapiensId());
        if (usuario.isPresent()) {
            var user = usuario.get();
            user.setUltimoAcesso(LocalDateTime.now(ZoneId.of(SAO_PAULO_ZONE_ID)));
            return usuarioRepository.save(user);
        }
        return criarUsuario(tokenDecoded);
    }

    private UsuarioEntity criarUsuario(TokenDecoded tokenDecoded) {
        UsuarioEntity novoUsuario = new UsuarioEntity();
        novoUsuario.setCpf(tokenDecoded.cpf());
        novoUsuario.setNome(tokenDecoded.nome());
        novoUsuario.setEmail(tokenDecoded.email());
        novoUsuario.setSapiensId(tokenDecoded.sapiensId());
        novoUsuario.setCriadoEm(LocalDateTime.now(ZoneId.of(SAO_PAULO_ZONE_ID)));
        novoUsuario.setUltimoAcesso(LocalDateTime.now(ZoneId.of(SAO_PAULO_ZONE_ID)));
        var setor = setorService.buscarSetorPorId(tokenDecoded.setorId(), tokenDecoded.token());
        novoUsuario.setSetor(setor);
        novoUsuario.setRole(UserRole.USER);
        return usuarioRepository.save(novoUsuario);
    }
}
