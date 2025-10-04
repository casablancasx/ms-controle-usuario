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


    public UnidadeEntity buscarUnidade(Long unidadeId, String nomeUnidade) {
        return unidadeRepository.findById(unidadeId).orElseGet(() -> criarUnidade(unidadeId, nomeUnidade));
    }

    private UnidadeEntity criarUnidade(Long unidadeId, String nomeUnidade) {
        return unidadeRepository.save(new UnidadeEntity(unidadeId, nomeUnidade));
    }
}
