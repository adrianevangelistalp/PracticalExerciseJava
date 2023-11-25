package com.backend.test.account.service;


import com.backend.test.account.dto.request.MovementRequestDto;
import com.backend.test.account.dto.response.MovementCompleteResponseDto;
import com.backend.test.account.dto.response.MovementResponseDto;

import java.util.Date;
import java.util.List;

public interface MovementService {

    public List<MovementCompleteResponseDto> findAll();

    public MovementCompleteResponseDto findById(Long id);

    public MovementCompleteResponseDto save(MovementRequestDto movement);

    public MovementCompleteResponseDto update(Long id, MovementRequestDto movement);

    public void delete(Long id);

    public List<MovementResponseDto> findByAccountIdAndDateBetween(Long id, Date dateFrom, Date dateTo);

}
