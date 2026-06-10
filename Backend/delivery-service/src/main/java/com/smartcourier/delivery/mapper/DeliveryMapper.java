package com.smartcourier.delivery.mapper;

import com.smartcourier.delivery.dto.DeliveryRequestDTO;
import com.smartcourier.delivery.dto.DeliveryResponseDTO;
import com.smartcourier.delivery.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * MapStruct mapper for Delivery entity ↔ DTO conversion.
 */
@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trackingNumber", ignore = true)
    @Mapping(target = "customerUsername", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "assignedAgent", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "currentHub", ignore = true)
    Delivery toEntity(DeliveryRequestDTO dto);

    @Mapping(target = "status", expression = "java(entity.getStatus() != null ? entity.getStatus().name() : null)")
    @Mapping(target = "createdAt", expression = "java(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null)")
    @Mapping(target = "currentHubId", expression = "java(entity.getCurrentHub() != null ? entity.getCurrentHub().getId() : null)")
    @Mapping(target = "currentHubName", expression = "java(entity.getCurrentHub() != null ? entity.getCurrentHub().getName() : null)")
    DeliveryResponseDTO toDto(Delivery entity);

    List<DeliveryResponseDTO> toDtoList(List<Delivery> entities);
}
