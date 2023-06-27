package org.example.carservice.dto.response;

import java.util.List;
import lombok.Data;
import org.example.carservice.model.Service.StatusService;

@Data
public class ServiceResponseDto {
    private Long id;
    private List<Long> orderIds;
    private List<Long> masterIds;
    private StatusService statusService;
    private double costService;
}
