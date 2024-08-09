package com.example.viages.gerenciamentoApi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "travel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String country;
    @Temporal(TemporalType.DATE)
    private Date start;
    @Temporal(TemporalType.DATE)
    private Date end;
    @OneToMany(mappedBy = "travel")
    private List<DestinyModel> destiny;
}