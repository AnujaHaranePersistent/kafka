package com.inventories.service;

import com.inventories.entity.BrandEntity;
import com.inventories.entity.BrandManufacturerEntity;
import com.inventories.repository.BrandManufacturerRepo;
import com.inventories.repository.BrandRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BrandManufacturerServiceTest {

    @Mock
    private BrandManufacturerRepo brandManufacturerRepo;

    @InjectMocks
    private BrandManufacturerService brandManufacturerService = new BrandManufacturerServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBrandEntity() {
        List<BrandManufacturerEntity>  brandManufacturerEntities = new ArrayList<BrandManufacturerEntity>();
        brandManufacturerEntities.add(Mockito.mock(BrandManufacturerEntity.class));
        brandManufacturerEntities.add(Mockito.mock(BrandManufacturerEntity.class));
        brandManufacturerEntities.add(Mockito.mock(BrandManufacturerEntity.class));
        when(brandManufacturerRepo.findAll()).thenReturn(brandManufacturerEntities);

        List<BrandManufacturerEntity> result = brandManufacturerService.getAllBrandManufacturer();
        assertEquals(3, result.size());
    }


    @Test
    public void testAddBrandManufacturerEntity() {

        BrandManufacturerEntity brandManufacturerEntity = Mockito.mock(BrandManufacturerEntity.class);
        when(brandManufacturerRepo.save(brandManufacturerEntity)).thenReturn(brandManufacturerEntity);
        BrandManufacturerEntity result = brandManufacturerService.addBrandManufacturer(brandManufacturerEntity);
        assertEquals(result, brandManufacturerEntity);
    }

}
