package br.gov.agu.nutec.mscontroledeusuario.mapper;

import br.gov.agu.nutec.mscontroledeusuario.dto.SetorResponse;
import br.gov.agu.nutec.mscontroledeusuario.dto.UnidadeResponse;
import br.gov.agu.nutec.mscontroledeusuario.dto.UserResponseDTO;
import br.gov.agu.nutec.mscontroledeusuario.entity.SetorEntity;
import br.gov.agu.nutec.mscontroledeusuario.entity.UnidadeEntity;
import br.gov.agu.nutec.mscontroledeusuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "setor", source = "setor")
    UserResponseDTO mapToResponse(UsuarioEntity usuario);

    @Mapping(target = "id", source = "setorId")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "unidade", source = "unidade")
    SetorResponse toDto(SetorEntity setor);

    @Mapping(target = "id", source = "unidadeId")
    @Mapping(target = "nome", source = "nome")
    UnidadeResponse toDto(UnidadeEntity unidade);

    List<UserResponseDTO> mapToResponse(List<UsuarioEntity> usuarios);
}
