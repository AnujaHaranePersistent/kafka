package com.inventories.controller;

import com.inventories.entity.BrandManufacturerEntity;
import com.inventories.kafka.KafkaProducer;
import com.inventories.service.BrandManufacturerService;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/brand/manufacturer")
@Api(value="inventories application", description="Operations pertaining to brand manufacturer in database")
public class BrandManufacturerController {

    @Autowired
    BrandManufacturerService brandManufacturerService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.topic}")
    String brandManufacturerTopic;

    @GetMapping(value = "")
    @ApiOperation(value = "View a list of brand manufacturer",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> getAllBrandManufacturer(){
        List<BrandManufacturerEntity> brandManufacturer = null;
        try {
            brandManufacturer = brandManufacturerService.getAllBrandManufacturer();
        } catch (Exception e) {
            log.error("An error occurred! {}", e.getMessage());

        }

        return new ResponseEntity<>(brandManufacturer, HttpStatus.OK);
    }

    @PostMapping(value = "/kafka", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "send brand manufacturer to kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> postBrandManufacturer(@ApiParam(value = "Brand Manufacturer object to send to kafka", required = true)
            @Valid @RequestBody BrandManufacturerEntity brandManufacturerEntity){
        BrandManufacturerEntity brandManufacturer = null;
        log.info(("Process add new brand manufacturer"));
      //  CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postBrandManufacturer("inventories", kafkaGroupId, brandManufacturerEntity);

        } catch (Exception e) {
            log.error("An error occurred! {}", e.getMessage());

        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "to add brand manufacturer",response = BrandManufacturerEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<?> addBrandManufacturer(@ApiParam(value = "Brand Manufacturer object to store in database", required = true)
            @Valid @RequestBody BrandManufacturerEntity brandManufacturerEntity){
        BrandManufacturerEntity brandManufacturer = null;
        log.info(("Process add new brand manufacturer"));
        BrandManufacturerEntity brandManufacturerEntity1=null;
        try {
            brandManufacturerEntity1=brandManufacturerService.addBrandManufacturer(brandManufacturerEntity);

        } catch (Exception e) {
            log.error("An error occurred! {}", e.getMessage());

        }
        return new ResponseEntity<>(brandManufacturerEntity1, HttpStatus.OK);
    }
}