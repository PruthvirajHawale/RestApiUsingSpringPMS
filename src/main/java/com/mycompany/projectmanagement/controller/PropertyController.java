package com.mycompany.projectmanagement.controller;

import com.mycompany.projectmanagement.Service.PropertyService;
import com.mycompany.projectmanagement.dto.PropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi")
public class PropertyController {

    @Value("${pms.dummy}")
    private String dummy;

    @Value("${spring.datasource.url}")
    private String dbUrl;


    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        propertyDTO = propertyService.saveProperty(propertyDTO);
//        System.out.println(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        System.out.println(dbUrl);
        System.out.println(dummy);
        propertyService.getAllProperty();
        List<PropertyDTO> propertyList = propertyService.getAllProperty();
//        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<List<PropertyDTO>>(propertyList,HttpStatus.OK);
        return new ResponseEntity<List<PropertyDTO>>(propertyList,HttpStatus.OK);
    }

    @GetMapping("/properties/user/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable Long userId){
        List<PropertyDTO> propertyList = propertyService.getAllPropertyById(userId);
//        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<List<PropertyDTO>>(propertyList,HttpStatus.OK);
        return new ResponseEntity<List<PropertyDTO>>(propertyList,HttpStatus.OK);
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updateProperty(propertyDTO,propertyId);
        return new ResponseEntity<PropertyDTO>(propertyDTO,HttpStatus.OK);
    }

    @PatchMapping("/properties/updateDesc/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO,propertyId);
        return new ResponseEntity<PropertyDTO>(propertyDTO,HttpStatus.OK);
    }
    @PatchMapping("/properties/updatePrice/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO,propertyId);
        return new ResponseEntity<PropertyDTO>(propertyDTO,HttpStatus.OK);
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> deleteProperty(@PathVariable("propertyId") Long Id){
         PropertyDTO propertyDTO = propertyService.deletePropertyById(Id);
        return new ResponseEntity<PropertyDTO>(propertyDTO,HttpStatus.OK);
    }
}
