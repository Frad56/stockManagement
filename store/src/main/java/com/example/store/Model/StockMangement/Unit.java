package com.example.store.Model.StockMangement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    @Enumerated(EnumType.STRING)
    private UnitName name;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "date_of_creation", updatable = false)
    private LocalDateTime dateOfCreation;

    @Enumerated(EnumType.STRING)
    private SizeType sizeType;

}
