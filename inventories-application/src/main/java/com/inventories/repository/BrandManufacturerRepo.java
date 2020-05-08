package com.inventories.repository;

import com.inventories.entity.BrandManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandManufacturerRepo extends JpaRepository<BrandManufacturerEntity, Integer> {
    @Query(value = "SELECT nextval('brand_manufacturer_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
