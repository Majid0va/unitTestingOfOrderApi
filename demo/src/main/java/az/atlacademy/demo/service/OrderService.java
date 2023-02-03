package az.atlacademy.demo.service;

import az.atlacademy.demo.mapper.OrderDto;
import az.atlacademy.demo.mapper.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderDto getById(Long orderId);

    OrderDto makeOrder(OrderRequest orderRequest);

    List<OrderDto>getAll();
}

