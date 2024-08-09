package com.example.viages.gerenciamentoApi.repositories;

import com.example.viages.gerenciamentoApi.models.DestinyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinyRepository extends JpaRepository<DestinyModel, Long> {
}
