package org.example.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Column(name = "problem_description")
    private String problemDescription;
    @Column(name = "date_acceptance")
    private LocalDateTime dateOfAcceptance;
    @Column(name = "date_completion")
    private LocalDateTime dateCompletion;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_services",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_order")
    private StatusOrder statusOrder;
    @Column(name = "cost_total")
    private BigDecimal costTotal;

    public enum StatusOrder {
        ACCEPTED,
        IN_PROGRESS,
        SUCCESSFULLY_COMPLETED,
        NOT_SUCCESSFULLY_COMPLETED
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", car=" + car.getId()
                + ", problemDescription='" + problemDescription + '\''
                + ", dateOfAcceptance=" + dateOfAcceptance
                + ", dateCompletion=" + dateCompletion
                + ", services=" + services.stream().map(Service::getId).collect(Collectors.toList())
                + ", products=" + products.stream().map(Product::getId).collect(Collectors.toList())
                + ", statusOrder=" + statusOrder
                + ", costTotal=" + costTotal
                + '}';
    }
}
