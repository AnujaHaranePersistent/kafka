package com.inventories.controller;

import com.inventories.entity.BrandEntity;
import com.inventories.service.BrandService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BrandControllerTest {

    @Autowired
    BrandController controller;

    @MockBean
    BrandService service;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBrands() {
        // Manager manager = Mockito.mock(Manager.class);
        List<BrandEntity> brandEntity = new ArrayList<BrandEntity>();
        brandEntity.add(Mockito.mock(BrandEntity.class));
        brandEntity.add(Mockito.mock(BrandEntity.class));
        brandEntity.add(Mockito.mock(BrandEntity.class));
        when(service.getAllByBrandName()).thenReturn(brandEntity);

        ResponseEntity<?> result = controller.getAllByBrandName();
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetBrandEntityById() {
        BrandEntity brandEntity = Mockito.mock(BrandEntity.class);
        when(service.findById(1)).thenReturn(brandEntity);
        ResponseEntity<?> result = null;
        try {
            result = controller.getBrandById(1);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // assertThat(result.getStatusCodeValue()).isEqualTo(400);
            e.printStackTrace();
        }

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void testGetBrandEntityById_IfNotFound() {
        // Manager manager = Mockito.mock(Manager.class);
        BrandEntity brandEntity = Mockito.mock(BrandEntity.class);
        when(service.findById(1)).thenReturn(brandEntity);
        ResponseEntity<?> result = null;
        try {
            result = controller.getBrandById(2);
            assertEquals(null, result.getBody());
            fail("Should have thrown an exception");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // assertThat(result.getStatusCodeValue()).isEqualTo(400);
            e.printStackTrace();
        }

    }


}
