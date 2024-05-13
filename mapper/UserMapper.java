package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.UserDto;
import kz.medet.onlineshop.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);
}
