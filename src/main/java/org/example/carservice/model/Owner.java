package org.example.carservice.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinTable(name = "owner_cars",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
    @OneToMany
    @JoinTable(name = "owner_orders",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", cars=" + cars.stream().map(Car::getId).collect(Collectors.toList()) +
                ", orders=" + orders.stream().map(Order::getId).collect(Collectors.toList()) +
                '}';
    }
}
