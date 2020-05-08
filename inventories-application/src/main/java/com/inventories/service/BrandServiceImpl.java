package com.inventories.service;

import com.inventories.entity.BrandEntity;
import com.inventories.repository.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BrandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepo brandRepo;

    public BrandEntity findAllByBrandNameIsLike(String name) {

        return brandRepo.findAllByBrandNameIsLike(name);
    }

    public List<BrandEntity> getAllByBrandName(){

        return brandRepo.findAll();
    }

    public BrandEntity findById(int id){
        return brandRepo.findById(id);
    }


    public BrandEntity addBrand(BrandEntity brand) {
        brand.setId(brandRepo.getNextSeriesId().intValue());
        return brandRepo.save(brand);
    }

    public BrandEntity updateBrand(BrandEntity brand) {
        return brandRepo.save(brand);
    }
}
