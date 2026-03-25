package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.CharacteristicValueDTO;
import com.example.store.Model.StockMangement.CharacteristicValue;

import java.util.List;

public interface CharacteristicValueService {

    CharacteristicValue saveCharacteristicValue(CharacteristicValueDTO characteristicValueDTO);

    CharacteristicValue findCharacteristicValueById(Long characteristicValueId);

    List<CharacteristicValue> fetchCharacteristicValueList();

    CharacteristicValue updateCharacteristicValue(CharacteristicValueDTO characteristicValueDTO,Long characteristicValueId);

    void deleteCharacteristicValueById(Long characteristicValueId);
}
