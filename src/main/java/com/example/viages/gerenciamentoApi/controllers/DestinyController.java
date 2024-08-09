package com.example.viages.gerenciamentoApi.controllers;

import com.example.viages.gerenciamentoApi.dtos.DestinyRecordDto;
import com.example.viages.gerenciamentoApi.models.DestinyModel;
import com.example.viages.gerenciamentoApi.repositories.DestinyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destiny")
public class DestinyController {

    @Autowired
    DestinyRepository destinyRepository;

    @GetMapping
    public ResponseEntity<List<DestinyModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(destinyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinyModel> getById(@PathVariable(value = "id") Long id) {
        DestinyModel destiny = destinyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destiny not found"));
        return ResponseEntity.status(HttpStatus.OK).body(destiny);
    }

    @PostMapping
    public ResponseEntity<DestinyModel> addDestiny(@RequestBody @Valid DestinyRecordDto destinyDTO) {
        DestinyModel destinyM = new DestinyModel();
        BeanUtils.copyProperties(destinyDTO, destinyM);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinyRepository.save(destinyM));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinyModel> updateDestiny(@PathVariable(value = "id") Long id, @RequestBody @Valid DestinyRecordDto destinyDto) {
        DestinyModel destiny = destinyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destiny not found"));
        BeanUtils.copyProperties(destinyDto, destiny);
        return ResponseEntity.status(HttpStatus.OK).body(destinyRepository.save(destiny));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDestiny(@PathVariable(value = "id") Long id) {
        DestinyModel destiny = destinyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destiny not found"));
        destinyRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Destiny deleted successfully");
    }
}
