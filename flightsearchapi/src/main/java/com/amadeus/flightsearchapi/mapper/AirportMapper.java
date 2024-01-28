package com.amadeus.flightsearchapi.mapper;

import com.amadeus.flightsearchapi.dto.airport.AirportCrudDto;
import com.amadeus.flightsearchapi.entity.Airport;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AirportMapper {
    Airport toEntity(AirportCrudDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Airport entity, AirportCrudDto updateDto);
}
