package az.atlacademy.demo.service;

import az.atlacademy.demo.client.Client;
import az.atlacademy.demo.mapper.OrderDto;
import az.atlacademy.demo.mapper.OrderMapper;
import az.atlacademy.demo.mapper.OrderRequest;
import az.atlacademy.demo.client.Product;
import az.atlacademy.demo.repository.OrderRepository;
import az.atlacademy.demo.util.InsufficientException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    private  Client client;
    private  Product product;

    @Override
    public OrderDto getById(Long orderId) {
        return orderMapper.getEntityToDto(orderRepository.findById(orderId).get());
    }

    @Override
    @Transactional
    public OrderDto makeOrder(OrderRequest orderRequest) {
        var products = product.getProduct(orderRequest.getProductId());
        if (products.getCount() < orderRequest.getCount()) {
            throw new InsufficientException("Not enough food in stock");
        }
        var customer = client.getCustomer(orderRequest.getCustomerId());
        var orderPrice = orderRequest.getCount() * products.getPrice();
        if (customer.getBalance().compareTo(BigDecimal.valueOf(orderPrice)) < 0) {
            throw new InsufficientException("Not enough money");
        }
        var orderExample = orderMapper.saveOrder(orderRequest);
        return orderMapper.getEntityToDto(orderRepository.save(orderExample));
    }

    @Override
    public List<OrderDto> getAll() {

        return orderMapper.getEntityListToDtoList(orderRepository.findAll());
    }
}
