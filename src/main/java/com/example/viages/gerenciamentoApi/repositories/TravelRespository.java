package com.example.viages.gerenciamentoApi.repositories;

import com.example.viages.gerenciamentoApi.dtos.DestinyRecordDto;
import com.example.viages.gerenciamentoApi.models.TravelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TravelRespository extends JpaRepository<TravelModel, Long> {
    List<TravelModel> findByStart(Date start);
    List<TravelModel> findByEnd(Date end);
    List<TravelModel> findByDestiny(DestinyRecordDto destiny);
}
