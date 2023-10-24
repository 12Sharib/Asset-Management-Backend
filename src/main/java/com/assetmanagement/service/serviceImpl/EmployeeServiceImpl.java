package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.dao.DesignationRepository;
import com.assetmanagement.dao.EmployeeRepository;
import com.assetmanagement.dao.PracticeUnitRepository;
import com.assetmanagement.dto.EmployeeDto;
import com.assetmanagement.entity.Designation;
import com.assetmanagement.entity.Employee;
import com.assetmanagement.entity.PracticeUnit;
import com.assetmanagement.response.EmployeeResponse;
import com.assetmanagement.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private PracticeUnitRepository practiceUnitRepository;
    @Autowired
    private DesignationRepository designationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeResponse allEmployees() {
        log.info("Started service of get all employees");
        // Get all employees, designations and practice units
        final List<Employee> employees = employeeRepository.findAll();
        final List<Designation> designations = designationRepository.findAll();
        final List<PracticeUnit> practiceUnits = practiceUnitRepository.findAll();

        final List<EmployeeDto> employeeDtos = new ArrayList<>();

        // Iterate employee list to convert dto
        employees.forEach(employee -> {
            final EmployeeDto employeeDto = new EmployeeDto();

            final Optional<Designation> optionalDesignation = designations.stream().filter(designation -> designation.getId().equals(employee.getDesignationId())).findFirst();
            final Optional<PracticeUnit> optionalPracticeUnit = practiceUnits.stream().filter(practiceUnit -> practiceUnit.getId().equals(employee.getPuId())).findFirst();

            // Set employee dto details
            if (optionalDesignation.isPresent() && optionalPracticeUnit.isPresent()){
                employeeDto.setDesignationName(optionalDesignation.get().getName());
                employeeDto.setPuName(optionalDesignation.get().getName());
            }
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setLocation(employeeDto.getLocation());

            employeeDtos.add(employeeDto);
        });

        log.info("Completed service of get all employees");
        return new EmployeeResponse(true, employeeDtos);
    }
}
