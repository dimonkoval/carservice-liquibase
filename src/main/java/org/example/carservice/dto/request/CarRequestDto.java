package org.example.carservice.dto.request;

import lombok.Data;

@Data
public class CarRequestDto {
    private String name;
    private String model;
    private int yearOfIssue;
    private String number;
    private Long ownerId;
}
