package com.example.store.Service.stockManagment.implementation;

import com.example.store.DTO.stockManagment.CharacteristicValueDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Model.StockMangement.CharacteristicValue;
import com.example.store.Model.StockMangement.ProductCharacteristic;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Repository.StockManagment.CharacteristicRepository;
import com.example.store.Repository.StockManagment.CharacteristicValueRepository;
import com.example.store.Service.stockManagment.interfaces.CharacteristicValueService;
import com.example.store.Service.stockManagment.interfaces.ProductCharacteristicService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CharacteristicValueServiceImpl implements CharacteristicValueService {


    private final CharacteristicValueRepository characteristicValueRepository;
    private final ProductCharacteristicService productCharacteristicService;
    private final ProductVariantService productVariantService;
    @Autowired
    public CharacteristicValueServiceImpl(CharacteristicValueRepository characteristicValueRepository,
                                          ProductCharacteristicService productCharacteristicService,
                                          ProductVariantService productVariantService){
        this.characteristicValueRepository=characteristicValueRepository;
            this.productCharacteristicService=productCharacteristicService;
            this.productVariantService=productVariantService;
    }

    private void mapDTOToCharacteristicValue(CharacteristicValueDTO characteristicValueDTO,
                                             CharacteristicValue characteristicValue){

        characteristicValue.setProductCharacteristic(productCharacteristicService.findProductCharacteristicById
                (characteristicValueDTO.getProductCharacteristicId()));
        characteristicValue.setProductVariant(productVariantService.findProductVariantById
                (characteristicValueDTO.getProductVariantId()));
        characteristicValue.setValue(characteristicValueDTO.getValue());
    }


    @Override
    public CharacteristicValue saveCharacteristicValue(CharacteristicValueDTO characteristicValue){
            CharacteristicValue characteristicValueDB = new CharacteristicValue();
            mapDTOToCharacteristicValue(characteristicValue,characteristicValueDB);
            return characteristicValueRepository.save(characteristicValueDB);
    }

    @Override
    public CharacteristicValue findCharacteristicValueById(Long characteristicValueId){
        return characteristicValueRepository.findById(characteristicValueId).orElseThrow(()->
                new ElementNotFoundException(characteristicValueId));
    }

    @Override
    public List<CharacteristicValue> fetchCharacteristicValueList(){
        return characteristicValueRepository.findAll();
    }
    @Override
    public CharacteristicValue updateCharacteristicValue(CharacteristicValueDTO characteristicValueDTO,Long characteristicValueId){
        CharacteristicValue characteristicValueDB = findCharacteristicValueById(characteristicValueId);
        mapDTOToCharacteristicValue(characteristicValueDTO,characteristicValueDB);
        return characteristicValueRepository.save(characteristicValueDB);
    }

    @Override
    public void deleteCharacteristicValueById(Long characteristicValueId){
       if(!characteristicValueRepository.existsById(characteristicValueId)){
           throw new ElementNotFoundException(characteristicValueId);
       }
       characteristicValueRepository.deleteById(characteristicValueId);
    }


}
