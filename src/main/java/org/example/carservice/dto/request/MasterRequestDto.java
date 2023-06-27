package org.example.carservice.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class MasterRequestDto {
    private Long id;
    private String name;
    private List<Long> orderIds;
}
