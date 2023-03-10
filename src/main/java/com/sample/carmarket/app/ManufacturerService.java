package com.sample.carmarket.app;

import com.sample.carmarket.entity.EngineType;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManufacturerService {
    @Autowired
    private DataManager dataManager;

    public Integer calculateCountOfElectricCars(UUID manufacturerId) {
        return dataManager.loadValue("select count(c) from Car c where c.model.manufacturer.id = :manufacturerId AND c.model.engineType = :electricCar", Integer.class)
                .parameter("manufacturerId", manufacturerId)
                .parameter("electricCar", EngineType.ELECTRIC)
                .one();
    }

    public Integer calculateCountOfGasolineCars(UUID manufacturerId) {
        return dataManager.loadValue("select count(c) from Car c where c.model.manufacturer.id = :manufacturerId AND c.model.engineType = :gasolineCar", Integer.class)
                .parameter("manufacturerId", manufacturerId)
                .parameter("gasolineCar", EngineType.GASOLINE)
                .one();
    }
}