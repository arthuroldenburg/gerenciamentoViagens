package com.example.viages.gerenciamentoApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destiny")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonBackReference
    @ManyToOne
    private TravelModel travel;

}