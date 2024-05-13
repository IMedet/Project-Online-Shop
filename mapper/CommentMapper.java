package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.CommentDto;
import kz.medet.onlineshop.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> comments);
}
