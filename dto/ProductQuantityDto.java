package kz.medet.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductQuantityDto {
    private Long id;
    private int quantity;
}
