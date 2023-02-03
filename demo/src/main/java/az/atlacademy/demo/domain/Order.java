package az.atlacademy.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long CustomerId;
    private Long ProductId;
    private int count;
    private Long price;
    private LocalDate orderedAt;

    @PrePersist
    void init() {
        setOrderedAt(LocalDate.now());
    }


}
