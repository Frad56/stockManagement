package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.CharacteristicDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Repository.StockManagment.CharacteristicRepository;
import com.example.store.Service.stockManagment.interfaces.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {


    private final CharacteristicRepository characteristicRepository;


    @Autowired
    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository){
        this.characteristicRepository=characteristicRepository;
    }


    private void mapDTOToCharacteristic(CharacteristicDTO characteristicDTO,Characteristic characteristic){
        characteristic.setName(characteristicDTO.getName());
        characteristic.setType(characteristicDTO.getType());
    }

    @Override
    public Characteristic saveCharacteristic(CharacteristicDTO characteristicDTO){
        Characteristic characteristic = new Characteristic();
        mapDTOToCharacteristic(characteristicDTO,characteristic);
        return characteristicRepository.save(characteristic);
    }

    @Override
    public Characteristic findCharacteristicById(Long characteristicId){
        return characteristicRepository.findById(characteristicId).orElseThrow(()->
                new ElementNotFoundException(characteristicId));
    }

    @Override
    public List<Characteristic> fetchCharacteristicList(){
        return characteristicRepository.findAll();
    }

    @Override
    public Characteristic updateCharacteristic(CharacteristicDTO characteristicDTO, Long characteristicId){
        Characteristic characteristicDB = findCharacteristicById(characteristicId);
        mapDTOToCharacteristic(characteristicDTO,characteristicDB);
        return characteristicRepository.save(characteristicDB);
    }


    @Override
    public void deleteCharacteristicById(Long characteristicId){
        if(!characteristicRepository.existsById(characteristicId)){
            throw new ElementNotFoundException(characteristicId);
        }

        characteristicRepository.deleteById(characteristicId);
    }



}
