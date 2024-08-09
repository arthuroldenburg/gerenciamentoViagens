package com.example.viages.gerenciamentoApi.controllers;

import com.example.viages.gerenciamentoApi.dtos.DestinyRecordDto;
import com.example.viages.gerenciamentoApi.dtos.TravelRecordDto;
import com.example.viages.gerenciamentoApi.models.DestinyModel;
import com.example.viages.gerenciamentoApi.models.TravelModel;
import com.example.viages.gerenciamentoApi.repositories.TravelRespository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    TravelRespository travelRespository;

    @GetMapping
    public ResponseEntity<List<TravelModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(travelRespository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelModel> getById(@PathVariable(value = "id") Long id) {
        TravelModel travel = travelRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel not found"));
        return ResponseEntity.status(HttpStatus.OK).body(travel);
    }

    @GetMapping("/starts/{date}")
    public ResponseEntity<List<TravelModel>> getByStartingDate(@PathVariable(value = "date")Date startDate) {
//        takes the travels by the starting date
        return ResponseEntity.status(HttpStatus.OK).body(travelRespository.findByStart(startDate));
    }

    @GetMapping("/ends/{date}")
    public ResponseEntity<List<TravelModel>> getByEndingDate(@PathVariable(value = "date")Date endsDate) {
//        takes the travels by the ending date
        return ResponseEntity.status(HttpStatus.OK).body(travelRespository.findByEnd(endsDate));
    }

    @GetMapping("/destiny/{name}")
    public ResponseEntity<List<TravelModel>> getByDestiny(@PathVariable(value = "name")DestinyRecordDto destinyDto) {
//        takes the travels by the destiny
        return ResponseEntity.status(HttpStatus.OK).body(travelRespository.findByDestiny(destinyDto));
    }

    @PostMapping
    public ResponseEntity<TravelModel> addTravel(@RequestBody @Valid TravelRecordDto travelDto) {
        TravelModel travelM = new TravelModel();
        if(travelDto.start().after(travelDto.end()) || travelDto.end().before(travelDto.start())) {
            throw new RuntimeException("The date is wrong");
        }
        BeanUtils.copyProperties(travelDto, travelM);
        return ResponseEntity.status(HttpStatus.CREATED).body(travelRespository.save(travelM));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelModel> update(@PathVariable(value = "id") Long id, @RequestBody @Valid TravelRecordDto travelDto) {
        TravelModel travel = travelRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel not found"));
        if(travelDto.start().after(travelDto.end()) || travelDto.end().before(travelDto.start())) {
            throw new RuntimeException("The date is wrong");
        }
        BeanUtils.copyProperties(travelDto, travel);
        return ResponseEntity.status(HttpStatus.OK).body(travelRespository.save(travel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDestiny(@PathVariable(value = "id") Long id) {
        TravelModel travel = travelRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel not found"));
        travelRespository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Travel deleted successfully");
    }
}
