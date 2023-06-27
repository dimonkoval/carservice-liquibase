package org.example.carservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class OwnerResponseDto {
    private Long id;
    private List<Long> carIds;
    private List<Long> orderIds;
}
