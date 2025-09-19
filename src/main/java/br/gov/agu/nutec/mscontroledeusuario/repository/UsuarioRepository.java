package br.gov.agu.nutec.mscontroledeusuario.repository;

import br.gov.agu.nutec.mscontroledeusuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findBySapiensId(Long sapiensId);

}
