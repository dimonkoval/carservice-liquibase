package org.example.carservice.dto.response;

import java.util.List;
import lombok.Data;
import org.example.carservice.model.Car;
import org.example.carservice.model.Order;

@Data
public class OwnerResponseDto {
    private Long id;
    private List<Car> cars;
    private List<Order> orders;
}
