package com.example.store.DTO.stockManagment;


import com.example.store.Model.StockMangement.SizeType;
import com.example.store.Model.StockMangement.UnitName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnitDTO {


    private UnitName name;
    private String symbol;
    private SizeType sizeType;

}
