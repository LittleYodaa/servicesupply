package pl.patrykkawula.servicesupply.order;

import jakarta.persistence.*;
import lombok.*;
import pl.patrykkawula.servicesupply.cartproduct.CartProduct;
import pl.patrykkawula.servicesupply.employee.Employee;
import pl.patrykkawula.servicesupply.orderproduct.OrderProduct;
import pl.patrykkawula.servicesupply.store.Store;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDate dateOfOrder;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    private Store store;
//    @OneToMany(mappedBy = "order")
//    private List<CartProduct> cartProducts;
@OneToMany(mappedBy = "order")
private List<OrderProduct> orderProducts;

}
