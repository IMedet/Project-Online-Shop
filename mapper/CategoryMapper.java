package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.CategoryDto;
import kz.medet.onlineshop.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);
}
