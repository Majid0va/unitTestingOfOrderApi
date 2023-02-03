package az.atlacademy.demo.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderRequest {
    private int count;
    private Long customerId;
    private Long productId;
}
