package com.tcs.check_in_check_out_system.service;

//import com.tcs.check_in_check_out_system.model.CheckInModel;
import com.tcs.check_in_check_out_system.model.CheckInModel;
import com.tcs.check_in_check_out_system.model.EmployeeModel;
//import com.tcs.check_in_check_out_system.repository.CheckInRepository;
import com.tcs.check_in_check_out_system.repository.CheckInRepository;
import com.tcs.check_in_check_out_system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.time.LocalDateTime.now;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    //1) Allows users to register a check-in //Post
    //2) Retrieve check-in and check-out records. //Get
    //3) Handle updating the check-out time when a person leaves the facility //Update
    //4) Provide functionality for deleting records if needed //Delete
    //5) Update each field
    //6) Get records by id

    //1
    public EmployeeModel registerCheckIn(String name, String department, String position, String email, String phone) {
        EmployeeModel employeeModel = new EmployeeModel();
        CheckInModel checkInModel = new CheckInModel();

        if(employeeRepository.existsByPhone(phone) || employeeRepository.existsByEmail(email)){
            throw new IllegalArgumentException("One user has the same number or the same email");
        }

        employeeModel.setName(name);
        employeeModel.setDepartment(department);
        employeeModel.setPosition(position);
        employeeModel.setEmail(email);
        employeeModel.setPhone(phone);
        employeeRepository.save(employeeModel);

        checkInModel.setCheckInTime(LocalDateTime.now());
        checkInModel.setCheckOutTime(LocalDateTime.now());
        checkInModel.setEmployee(employeeModel);
        checkInModel.setPerson(employeeModel.getId());
        employeeModel.getCheckIns().add(checkInModel);


        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel registerCheckIn(Long id) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        employeeRepository.save(employeeModel);

        CheckInModel checkInModel = new CheckInModel();
        checkInModel.setCheckInTime(LocalDateTime.now());
        checkInModel.setCheckOutTime(LocalDateTime.now());
        checkInModel.setEmployee(employeeModel);
        checkInModel.setPerson(employeeModel.getId());

        employeeModel.getCheckIns().add(checkInModel);

        return employeeRepository.save(employeeModel);
    }


    //2
    public List<EmployeeModel> getRecords(){
        List<EmployeeModel> records = new ArrayList<>();
        for(EmployeeModel employeeModel : employeeRepository.findAll()){
            records.add(employeeModel);
        }
        return records;
    }


    //4
    public void deleteRecord(Long id){
        employeeRepository.deleteById(id);
    }

    //5
    public EmployeeModel updateName(Long id, String name) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        employeeModel.setName(name);
        EmployeeModel updatedPerson = employeeRepository.save(employeeModel);
        return updatedPerson;
    }

    public EmployeeModel updatePosition(Long id, String position){
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        employeeModel.setPosition(position);
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel updateEmail(Long id, String  email) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(employeeRepository.existsByEmail(email)){
            throw new IllegalArgumentException("One user has the same email");
        }
        employeeModel.setEmail(email);
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel updatePhone(Long id, String  phone) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(employeeRepository.existsByPhone(phone)){
            throw new IllegalArgumentException("One user has the same number");
        }
        employeeModel.setPhone(phone);
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel updateDepartment(Long id, String  department) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        employeeModel.setDepartment(department);
        return employeeRepository.save(employeeModel);
    }

    //6
    public List<EmployeeModel> getRecordId(Long id){
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<EmployeeModel> records = new LinkedList<>();
        records.add(employeeModel);
        return records;
    }


}
