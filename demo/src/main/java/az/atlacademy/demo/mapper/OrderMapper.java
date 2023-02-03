package az.atlacademy.demo.mapper;

import az.atlacademy.demo.domain.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderDto getRequestToDto(OrderRequest orderRequest);

    Order getDtoToEntity(OrderDto orderDto);

    OrderDto getEntityToDto(Order order);

    List<OrderDto>getEntityListToDtoList(List<Order>list);

    Order saveOrder(OrderRequest orderRequest);

}
