package org.example.carservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.example.carservice.model.Order.StatusOrder;

@Data
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDateTime dateOfAcceptance;
    private LocalDateTime dateCompletion;
    private List<Long> serviceIds;
    private List<Long> productIds;
    private StatusOrder statusOrder;
    private BigDecimal costTotal;
}
