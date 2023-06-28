package org.example.carservice.model;

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
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "services")
    private List<Order> orders;
    @ManyToMany
    @JoinTable(name = "service_masters",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "master_id"))
    private List<Master> masters;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_service")
    private StatusService statusService;
    @Column(name = "cost_service")
    private double costService;

    public enum StatusService {
        PAID, NOT_PAID
    }

    @Override
    public String toString() {
        return "Service{"
                + "id=" + id
                + ", orders=" + orders.stream().map(Order::getId).collect(Collectors.toList())
                + ", masters=" + masters.stream().map(Master::getId).collect(Collectors.toList())
                + ", statusService=" + statusService
                + ", costService=" + costService
                + '}';
    }
}
