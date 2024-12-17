package com.example.MVCTutorial.MVCTutorial.controller;

import com.example.MVCTutorial.MVCTutorial.advice.ApiResponse;
import com.example.MVCTutorial.MVCTutorial.dto.DepartmentDto;
import com.example.MVCTutorial.MVCTutorial.exception.ResourceNotFoundException;
import com.example.MVCTutorial.MVCTutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getDepartments(){
        List<DepartmentDto> finalData = departmentService.getAllDepartments();
        if (null == finalData)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(finalData);
    }

    @PostMapping("/add")
    public ResponseEntity<?> PostDepartment( @RequestBody @Valid DepartmentDto dto){

        DepartmentDto finalDto = departmentService.createDepartment(dto);
        return new ResponseEntity<>(finalDto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable double id){
        DepartmentDto dto =departmentService.getAllDepartmentById(id);
        if(dto == null){
            throw new ResourceNotFoundException("Department with id "+ (int)id +" not found");
        }
        else{
            return new ResponseEntity<>(dto,HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable double id){
        boolean deleted =  departmentService.deleteDepartmentById(id);
        if (deleted)
            return ResponseEntity.ok(null);
        else
            throw new ResourceNotFoundException("Department with id "+ (int)id +" not found");
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<?> updateDepartmentById(@PathVariable double departmentId, @RequestBody @Valid DepartmentDto inputDepartmentDTO){

        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentId,inputDepartmentDTO));
    }

}
