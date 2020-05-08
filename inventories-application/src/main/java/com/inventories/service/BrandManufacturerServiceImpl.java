package com.inventories.service;

import com.inventories.entity.BrandManufacturerEntity;
import com.inventories.repository.BrandManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BrandManufacturerService")
public class BrandManufacturerServiceImpl implements BrandManufacturerService{

    @Autowired
    BrandManufacturerRepo brandManufacturerRepo;

    public List<BrandManufacturerEntity> getAllBrandManufacturer(){
        return brandManufacturerRepo.findAll();
    }

    public BrandManufacturerEntity addBrandManufacturer(BrandManufacturerEntity brandManufacturer) {
        brandManufacturer.setId(brandManufacturerRepo.getNextSeriesId().intValue());
        return brandManufacturerRepo.save(brandManufacturer);
    }
}
