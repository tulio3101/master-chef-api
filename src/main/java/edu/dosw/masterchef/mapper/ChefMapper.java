package edu.dosw.masterchef.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.masterchef.model.dto.Request.ChefRequestDTO;
import edu.dosw.masterchef.model.dto.Response.ChefResponseDTO;
import edu.dosw.masterchef.model.entity.Chef;

@Mapper(componentModel = "spring")
public interface ChefMapper {

    Chef toEntity(ChefRequestDTO dto);

    ChefResponseDTO toDto(Chef entity);

    List<Chef> toEntityList(List<ChefRequestDTO> dto);

    List<ChefResponseDTO> toDtoList(List<Chef> entity);

}
