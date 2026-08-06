package com.soldaMaster.solda.mapper;

import org.mapstruct.Mapper;

import com.soldaMaster.solda.dto.RolResponse;
import com.soldaMaster.solda.entity.Roles;

@Mapper(componentModel = "spring")
public interface RolMapper {
     RolResponse toResponse(Roles rol);
}
