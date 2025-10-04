package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.adpter.SuperSapiensAdapter;
import br.gov.agu.nutec.mscontroledeusuario.entity.SetorEntity;
import br.gov.agu.nutec.mscontroledeusuario.entity.UnidadeEntity;
import br.gov.agu.nutec.mscontroledeusuario.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetorService {

    private final UnidadeService unidadeService;
    private final SetorRepository setorRepository;
    private final SuperSapiensAdapter adapter;


    public SetorEntity buscarSetorPorId(Long setorId, String token) {
        return setorRepository.findById(setorId).orElseGet(() -> criarSetor(setorId, token));
    }

    private SetorEntity criarSetor(Long setorId, String token) {
        UnidadeEntity unidade = unidadeService.buscarUnidade(setorId, token);
        String nomeSetor = adapter.getNomeSetor(setorId, token);
        return setorRepository.save(new SetorEntity(setorId,nomeSetor,unidade));
    }
}
