package kz.medet.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class OrderDto {
    private Long id;
    private Timestamp orderDate;
    private String status;
}
