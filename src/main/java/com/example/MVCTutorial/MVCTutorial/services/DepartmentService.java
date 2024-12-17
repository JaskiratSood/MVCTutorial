package com.example.MVCTutorial.MVCTutorial.services;

import com.example.MVCTutorial.MVCTutorial.config.MapperConfig;
import com.example.MVCTutorial.MVCTutorial.dto.DepartmentDto;
import com.example.MVCTutorial.MVCTutorial.entity.Department;
import com.example.MVCTutorial.MVCTutorial.exception.ResourceNotFoundException;
import com.example.MVCTutorial.MVCTutorial.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public List<DepartmentDto> getAllDepartments() {
       List<Department> Departments =  departmentRepository.findAll();
        if(Departments.isEmpty())
            throw new ResourceNotFoundException("No Values found");
        else
            return Departments.stream()
                    .map(department -> mapper.map(department,DepartmentDto.class))
                    .collect(Collectors.toList());
    }


    public DepartmentDto createDepartment(@Valid DepartmentDto dto) {
        try {
            Department entity = mapper.map(dto, Department.class);
            DepartmentDto dto2 = mapper.map(departmentRepository.save(entity), DepartmentDto.class);
            return dto2;
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Unable to save with name "+ dto.getName());
        }
    }

    public DepartmentDto getAllDepartmentById(Double id) {
        Optional<Department> result = departmentRepository.findById(id);
        DepartmentDto dto = result.map(department -> mapper.map(department,DepartmentDto.class)).orElse(null);
        return dto;
    }

    public boolean deleteDepartmentById(Double id) {
        if (existById(id)){
            departmentRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public DepartmentDto updateDepartmentById(double departmentId, DepartmentDto inputDepartmentDTO) {
        if(existById(departmentId)){
           Department department =  mapper.map(inputDepartmentDTO,Department.class);
           department.setId(departmentId);
           departmentRepository.save(department);

           return mapper.map(department,DepartmentDto.class);
        }
        else{
            throw new ResourceNotFoundException("Resource with id "+ departmentId+ "not found");
        }
    }

    private boolean existById(Double id){
        return departmentRepository.existsById(id);
    }
}
