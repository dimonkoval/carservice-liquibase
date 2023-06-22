package org.example.carservice.dto.request;

import lombok.Data;
import org.example.carservice.model.Owner;

@Data
public class CarRequestDto {
    private String name;
    private String model;
    private int yearOfIssue;
    private String number;
    private Owner owner;
}
