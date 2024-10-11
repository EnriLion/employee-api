package com.tcs.check_in_check_out_system.service;

import com.tcs.check_in_check_out_system.model.CheckInModel;
import com.tcs.check_in_check_out_system.model.EmployeeModel;
import com.tcs.check_in_check_out_system.repository.CheckInRepository;
import com.tcs.check_in_check_out_system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    @Autowired
//    private CheckInRepository checkInRepository;

    //1) Allows users to register a check-in //Post
    //2) Retrieve check-in and check-out records. //Get
    //3) Handle updating the check-out time when a person leaves the facility //Update
    //4) Provide functionality for deleting records if needed //Delete
    //5) Update each field
    //6) Get records by id

    //1
    public EmployeeModel registerCheckIn(String name, String department, String position, String email, String phone) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setName(name);
//        employeeModel.setCheckInTime(LocalDateTime.now());
//        employeeModel.setCheckOutTime(LocalDateTime.now());
        employeeModel.setDepartment(department);
        employeeModel.setPosition(position);
        employeeModel.setEmail(email);
        employeeModel.setPhone(phone);
        CheckInModel checkInModel = new CheckInModel();
        checkInModel.setCheckInTime(LocalDateTime.now());
        checkInModel.setCheckOutTime(LocalDateTime.now());
        checkInModel.setStatus(false);
        employeeModel.getCheckIns().add(checkInModel);
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel registCheckIn(Long id) {
        // Fetch the existing employee
        EmployeeModel employeeModel = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Create a new CheckInModel
        CheckInModel checkInModel = new CheckInModel();
        checkInModel.setCheckInTime(LocalDateTime.now()); // Or set to LocalDateTime.now()
        checkInModel.setCheckOutTime(null); // Set to null or manage based on your logic
        checkInModel.setStatus(false); // Set initial status

        // Link back to the employee if needed
        checkInModel.setEmployee(employeeModel);

        // Add the new check-in to the employee's existing check-ins
        employeeModel.getCheckIns().add(checkInModel);

        // Save the updated employee model
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

    //3
    public EmployeeModel updateCheckOut(Long id) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
//        employeeModel.setCheckOutTime(now());
        EmployeeModel updatedPerson = employeeRepository.save(employeeModel);
        return updatedPerson;
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
        employeeModel.setEmail(email);
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel updatePhone(Long id, String  phone) {
        EmployeeModel employeeModel = employeeRepository.findById(id).orElseThrow(NoSuchElementException::new);
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
