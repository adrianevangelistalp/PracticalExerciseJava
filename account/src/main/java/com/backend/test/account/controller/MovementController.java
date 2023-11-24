package com.backend.test.account.controller;

import com.backend.test.account.dto.request.MovementRequestDto;
import com.backend.test.account.dto.response.MovementResponseDto;
import com.backend.test.account.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/movements")
public class MovementController {

    private final MovementService movementService;

    @GetMapping
    public ResponseEntity<List<MovementResponseDto>> findAll() {
        return ResponseEntity.ok().body(movementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementResponseDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(movementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovementResponseDto> save(@RequestBody MovementRequestDto movementRequestDto) {
        return ResponseEntity.ok().body(movementService.save(movementRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovementResponseDto> update(@PathVariable("id") Long id, @RequestBody MovementRequestDto movementRequestDto) {
        return ResponseEntity.ok().body(movementService.update(id, movementRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        movementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
