package com.example.viages.gerenciamentoApi.dtos;

import com.example.viages.gerenciamentoApi.models.DestinyModel;

import java.util.Date;

public record TravelRecordDto(Long id, String title, String country, Date start, Date end, DestinyModel destiny) {
}
