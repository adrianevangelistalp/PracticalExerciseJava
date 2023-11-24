package com.backend.test.account.service;


import com.backend.test.account.dto.request.MovementRequestDto;
import com.backend.test.account.dto.response.MovementResponseDto;

import java.util.Date;
import java.util.List;

public interface MovementService {

    public List<MovementResponseDto> findAll();

    public MovementResponseDto findById(Long id);

    public MovementResponseDto save(MovementRequestDto movement);

    public MovementResponseDto update(Long id, MovementRequestDto movement);

    public void delete(Long id);

    public List<MovementResponseDto> findByAccountIdAndDateBetween(Long id, Date dateFrom, Date dateTo);

}
