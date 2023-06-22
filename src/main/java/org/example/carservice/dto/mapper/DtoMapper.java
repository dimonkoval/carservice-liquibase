package org.example.carservice.dto.mapper;

public interface DtoMapper<P, S, Q> {
    P toModel(Q q);

    S toDto(P p);
}
