package com.inventories.controller;

import com.inventories.entity.BrandEntity;
import com.inventories.kafka.KafkaProducer;
import com.inventories.service.BrandService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/brand")
@Api(value="inventories application", description="Operations pertaining to products in inventories")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.topic}")
    String brandTopic;


    @GetMapping(value="")
    @ApiOperation(value = "View a list of brand",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> getAllByBrandName(){
         log.info("Fetching all brands");
        List<BrandEntity> brand=null;
        try {
            brand = brandService.getAllByBrandName();
        } catch (Exception e){
            log.error("An error occurred! {}", e.getMessage());
        }

        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    @ApiOperation(value = "get brand by ID",response = BrandEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> getBrandById(@ApiParam(value = "Brand ID ", required = true)
            @PathVariable("id") int id ){
        log.info("Fetching brand with ID {}", id);
        BrandEntity brand = null;
        try{

             brand = brandService.findById(id);
        } catch (Exception e){
            log.error("An error occurred! {}", e.getMessage());
        }
        return new ResponseEntity<BrandEntity>(brand, HttpStatus.OK);
    }
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "add brand in database table",response = BrandEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> addBrand(@ApiParam(value = "Brand object store in database table", required = true)
           @Valid @RequestBody BrandEntity brandEntity){
        log.info(("Process add new brand"));

        BrandEntity res=null;
        try {
           res= brandService.addBrand(brandEntity);
        } catch (Exception e){
           log.error("An error occurred! {}", e.getMessage());

        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping(value = "/kafka", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "to send brand to kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> addLayeredBrand(
            @ApiParam(value = "Brand object to send to kafka", required = true) @Valid @RequestBody BrandEntity brandEntity){
        log.info(("Process add new brand"));
        try {
          kafkaProducer.postBrand(brandTopic, "1", brandEntity);
        } catch (Exception e){
           log.error("An error occurred! {}", e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
