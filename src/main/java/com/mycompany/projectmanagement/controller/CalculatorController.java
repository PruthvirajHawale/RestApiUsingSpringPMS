package com.mycompany.projectmanagement.controller;

import com.mycompany.projectmanagement.dto.CalulatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vi/calculator") // Class level mapping of a url to a controller class
public class CalculatorController {

    @GetMapping("/add")  // method level mapping of a url to a controller function
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2){
        return num1 + num2;
    }

    @GetMapping("/sub/{num1}/{num2}") // Map the values of url to java variables by Path variable method
    public Double substract(@PathVariable("num1") Double num1,@PathVariable("num2") Double num2){
        Double  result = null;
        if(num1 > num2){
            return num1-num2;
        }else{
            return num2-num1;
        }
    }

    @GetMapping("/max/{num1}") // Url Mapping using both @RequestVariable and @PathVariable
    public String findMax(@PathVariable("num1") Double num1,@RequestParam("num2") Double num2){
        if(num1 > num2){
            return "num1 is greater";
        }else{
            return "num2 is greater";
        }
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalulatorDTO calulatorDTO){
        Double result = null;
        result = calulatorDTO.getNum1() * calulatorDTO.getNum2() * calulatorDTO.getNum3() * calulatorDTO.getNum4();
        ResponseEntity<Double> responseEntity  = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }


}
