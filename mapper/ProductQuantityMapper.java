package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.ProductQuantityDto;
import kz.medet.onlineshop.model.ProductQuantity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductQuantityMapper {
    ProductQuantityDto toDto(ProductQuantity productQuantity);

    ProductQuantity toEntity(ProductQuantityDto productQuantityDto);

    List<ProductQuantityDto> toDtoList(List<ProductQuantity> productQuantities);
}
