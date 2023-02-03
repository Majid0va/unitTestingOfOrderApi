package az.atlacademy.demo.client;

import az.atlacademy.demo.domain.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product", url = "")
public interface Product {
    ProductResponse getProduct(Long id);
}
