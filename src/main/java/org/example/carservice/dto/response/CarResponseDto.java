package org.example.carservice.dto.response;

import lombok.Data;
import org.example.carservice.model.Owner;

@Data
public class CarResponseDto {
    private Long id;
    private String name;
    private String model;
    private int yearOfIssue;
    private String number;
    private Owner owner;
}
