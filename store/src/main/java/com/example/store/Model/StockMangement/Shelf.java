package com.example.store.Model.StockMangement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="shelf")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shelfId;

    @Column(name = "Shelf_name_by_aisle")
    private String ShelfNameByAisle;

    @ManyToOne
    @JoinColumn(name = "aisle_id", nullable = false)
    private Aisle aisle;



}
