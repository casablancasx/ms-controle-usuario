package br.gov.agu.nutec.mscontroledeusuario.service;

import br.gov.agu.nutec.mscontroledeusuario.adpter.SuperSapiensAdapter;
import br.gov.agu.nutec.mscontroledeusuario.entity.UnidadeEntity;
import br.gov.agu.nutec.mscontroledeusuario.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final SuperSapiensAdapter adapter;


    public UnidadeEntity buscarUnidade(Long unidadeId, String token) {
        return unidadeRepository.findById(unidadeId).orElseGet(() -> criarUnidade(unidadeId, token));
    }

    private UnidadeEntity criarUnidade(Long unidadeId, String token) {
        String nomeUnidade = adapter.getNomeUnidade(unidadeId,token);
        return unidadeRepository.save(new UnidadeEntity(unidadeId, nomeUnidade));
    }
}
