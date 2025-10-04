package br.gov.agu.nutec.mscontroledeusuario.repository;

import br.gov.agu.nutec.mscontroledeusuario.entity.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Long> {


}
