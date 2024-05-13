package kz.medet.onlineshop.mapper;

import kz.medet.onlineshop.dto.OrderDto;
import kz.medet.onlineshop.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto tpDto(Order order);

    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);
}
