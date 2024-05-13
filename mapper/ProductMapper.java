package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.ProductDto;
import kz.medet.onlineshop.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);
}
