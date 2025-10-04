package br.gov.agu.nutec.mscontroledeusuario.repository;

import br.gov.agu.nutec.mscontroledeusuario.entity.SetorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<SetorEntity, Long> {


}
