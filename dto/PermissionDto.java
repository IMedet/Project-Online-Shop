package kz.medet.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PermissionDto {
    private Long id;
    private String permission;
}
