package com.example.store.Service.stockManagment.implementation;

import com.example.store.DTO.stockManagment.UnitDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Unit;
import com.example.store.Repository.StockManagment.UnitRepository;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    private void mapUnitDTOToUnit(UnitDTO unitDTO, Unit unit) {
        unit.setName(unitDTO.getName());
        unit.setSymbol(unitDTO.getSymbol());
        unit.setSizeType(unitDTO.getSizeType());
    }

    @Override
    public Unit saveUnit(UnitDTO unitDTO) {
        Unit unit = new Unit();
        mapUnitDTOToUnit(unitDTO, unit);
        return unitRepository.save(unit);
    }

    @Override
    public Unit findUnitById(Long unitId) {
        return unitRepository.findById(unitId).orElseThrow(()->
                new RuntimeException("Unit not found with id: " + unitId));
    }

    @Override
    public Unit updateUnit(UnitDTO unitDTO, Long unitId) {
        Unit existingUnit = findUnitById(unitId);
        mapUnitDTOToUnit(unitDTO, existingUnit);
        return unitRepository.save(existingUnit);
    }

    @Override
    public void deleteUnitById(Long unitId) {
        if(!unitRepository.existsById(unitId)) {
            throw new ElementNotFoundException("Unit not found with id: " + unitId);
        }
        unitRepository.deleteById(unitId);
    }

    @Override
    public List<Unit> fetchUnitList() {
        return unitRepository.findAll();
    }

}
