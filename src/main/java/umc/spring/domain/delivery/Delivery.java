package umc.spring.domain.delivery;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import umc.spring.domain.order.Order;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
