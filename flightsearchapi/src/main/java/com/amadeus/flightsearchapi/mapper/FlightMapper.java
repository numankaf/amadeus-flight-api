package com.amadeus.flightsearchapi.mapper;
import com.amadeus.flightsearchapi.dto.flight.FlightCrudDto;
import com.amadeus.flightsearchapi.entity.Flight;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FlightMapper {
    Flight toEntity(FlightCrudDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Flight entity, FlightCrudDto updateDto);
}
