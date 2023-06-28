package org.example.carservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private int yearOfIssue;
    private String number;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", model='" + model + '\''
                + ", yearOfIssue=" + yearOfIssue
                + ", number='" + number + '\''
                + ", owner=" + owner.getId()
                + '}';
    }
}
