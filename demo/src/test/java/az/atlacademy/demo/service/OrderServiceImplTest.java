package az.atlacademy.demo.service;

import az.atlacademy.demo.client.Client;
import az.atlacademy.demo.client.Product;
import az.atlacademy.demo.domain.Order;
import az.atlacademy.demo.mapper.OrderDto;
import az.atlacademy.demo.mapper.OrderMapper;
import az.atlacademy.demo.mapper.OrderRequest;
import az.atlacademy.demo.repository.OrderRepository;
import az.atlacademy.demo.util.InsufficientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;


class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Client client;
    @Mock
    private Product product;

    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void givenGetByIdWhenOrderFoundThenReturnOrderDto() {
        //arrange
        var order = new Order();
        order.setId(1L);
        order.setCount(1);
        order.setPrice(1L);
        order.setOrderedAt(LocalDate.now());

        Mockito.doReturn(Optional.of(order)).when(orderRepository.findById(ArgumentMatchers.anyLong()));
        //act
        var orderDto = orderService.getById(1L);

        //assert
        Assertions.assertNotNull(orderDto);
    }

    @Test
    public void givenMakeOrderWhenMakingOrderRequestThenReturnOrderDtoProduct() {
        var orderRequest = new OrderRequest();
        orderRequest.setCount(1);
        orderRequest.setCustomerId(1L);
        orderRequest.setProductId(1L);

        var orderDto = new OrderDto();
        orderDto.setCount(orderRequest.getCount());
        orderDto.setCustomerId(orderRequest.getCustomerId());
        orderDto.setProductId(orderRequest.getProductId());

        Mockito.when(orderRepository.save(orderMapper.saveOrder(orderRequest))).thenReturn(Optional.of(orderDto));

        var products = product.getProduct(orderRequest.getProductId());
        boolean condition = products.getCount() < orderRequest.getCount();

        Assertions.assertEquals(condition, true);
        Assertions.assertThrows(InsufficientException.class, () -> orderService.makeOrder(orderRequest), "Not enough food in stock");

        verify(orderRepository, Mockito.times(1)).save(orderMapper.saveOrder(orderRequest));
    }

    @Test
    public void givenMakeOrderWhenMakingOrderRequestThenReturnOrderDtoClient() {
        var orderRequest = new OrderRequest();
        orderRequest.setCount(1);
        orderRequest.setCustomerId(1L);
        orderRequest.setProductId(1L);

        var orderDto = new OrderDto();
        orderDto.setCount(orderRequest.getCount());
        orderDto.setCustomerId(orderRequest.getCustomerId());
        orderDto.setProductId(orderRequest.getProductId());

        Mockito.when(orderRepository.save(orderMapper.saveOrder(orderRequest))).thenReturn(Optional.of(orderDto));

        var products = product.getProduct(orderRequest.getProductId());
        var customer = client.getCustomer(orderRequest.getCustomerId());
        var orderPrice = orderRequest.getCount() * products.getPrice();
        boolean condition = customer.getBalance().compareTo(BigDecimal.valueOf(orderPrice)) < 0;

        Assertions.assertEquals(condition, true);
        Assertions.assertThrows(InsufficientException.class, () -> orderService.makeOrder(orderRequest), "Not enough money");

        verify(orderRepository, Mockito.times(1)).save(orderMapper.saveOrder(orderRequest));
    }

    @Test
    void getAllTutorials() {
        orderService.getAll();
        verify(orderRepository).findAll();
    }
}