package com.inventories.service;

import com.inventories.entity.BrandEntity;
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
public class BrandServiceTest {

    @Mock
    private BrandRepo brandRepo;

    @InjectMocks
    private BrandService service = new BrandServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBrandEntity() {
        List<BrandEntity> brandEntities = new ArrayList<BrandEntity>();
        brandEntities.add(Mockito.mock(BrandEntity.class));
        brandEntities.add(Mockito.mock(BrandEntity.class));
        brandEntities.add(Mockito.mock(BrandEntity.class));
        when(brandRepo.findAll()).thenReturn(brandEntities);

        List<BrandEntity> result = service.getAllByBrandName();
        assertEquals(3, result.size());
    }

    @Test
    public void testGetBrandEntityById() {
        BrandEntity brandEntity = Mockito.mock(BrandEntity.class);
        when(brandRepo.findById(1)).thenReturn(brandEntity);
        BrandEntity result = service.findById(1);
        assertEquals(result, brandEntity);


    }

    @Test
    public void testGetBrandEntityById_IfNotFound() {
        BrandEntity brandEntity = Mockito.mock(BrandEntity.class);
        when(brandRepo.findById(2)).thenReturn(null);
        BrandEntity result = service.findById(2);
        assertEquals(null, result);

    }


    @Test
    public void testSaveBrandEntity() {
        // Manager manager = new Manager(101, "abc", "abc");
        BrandEntity brandEntity = Mockito.mock(BrandEntity.class);
        when(brandRepo.save(brandEntity)).thenReturn(brandEntity);
        BrandEntity result = service.addBrand(brandEntity);
        assertEquals(result, brandEntity);
    }

    @Test
    public void testUpdateBrandEntity() {
        BrandEntity brandEntity = Mockito.mock(BrandEntity.class);
        when(brandRepo.save(brandEntity)).thenReturn(brandEntity);
        BrandEntity result = service.updateBrand(brandEntity);
        assertEquals(result, brandEntity);

    }
}
