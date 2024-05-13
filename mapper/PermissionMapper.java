package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.PermissionDto;
import kz.medet.onlineshop.model.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toDto(Permission permission);

    Permission toEntity(PermissionDto permissionDto);

    List<PermissionDto> toDtoList(List<Permission> permissions);
}
