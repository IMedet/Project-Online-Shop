package kz.medet.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CategoryDto {
    private Long id;
    private String name;
}
