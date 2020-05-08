package com.inventories.service;

import com.inventories.entity.BrandEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    List<BrandEntity> getAllByBrandName();
    BrandEntity addBrand(BrandEntity brand);
    BrandEntity findById(int id);
    BrandEntity updateBrand(BrandEntity brandEntity);
}
