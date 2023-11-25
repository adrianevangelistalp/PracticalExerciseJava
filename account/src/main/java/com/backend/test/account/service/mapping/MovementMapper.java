package com.backend.test.account.service.mapping;

import com.backend.test.account.dto.request.MovementRequestDto;
import com.backend.test.account.dto.response.MovementCompleteResponseDto;
import com.backend.test.account.dto.response.MovementResponseDto;
import com.backend.test.account.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    List<MovementResponseDto> toResponseDto(List<Movement> movements);

    MovementResponseDto toResponseDto(Movement movement);

    Movement toEntity(MovementRequestDto movement);

    @Mapping(source = "account.id", target = "accountId")
    MovementCompleteResponseDto toCompleteResponseDto(Movement save);

    List<MovementCompleteResponseDto> toCompleteResponseDto(List<Movement> all);
}
