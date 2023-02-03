package az.atlacademy.demo.client;

import az.atlacademy.demo.domain.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customer",url = "")
public interface Client {
    CustomerResponse getCustomer(Long id);
}
