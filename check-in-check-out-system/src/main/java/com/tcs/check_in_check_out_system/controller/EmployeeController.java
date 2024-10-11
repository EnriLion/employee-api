package com.tcs.check_in_check_out_system.controller;

import com.tcs.check_in_check_out_system.model.EmployeeModel;
import com.tcs.check_in_check_out_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/people")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

//    @PostMapping("/checkin")
//    public ResponseEntity<EmployeeModel> loginCheckIn(@RequestParam String name , @RequestParam String department, @RequestParam String position, @RequestParam String email, @RequestParam String phone){
//        if (name.isEmpty() || department.isEmpty() || position.isEmpty() || email.isEmpty() || phone.isEmpty()) {
//           return  ResponseEntity.notFound().build();
//        } else {
//            EmployeeModel employeeModel = employeeService.registerCheckIn(name,department,position,email,phone);
//            return ResponseEntity.ok(employeeModel);
//        }
//    }

    @PostMapping("/checking/{id}")
    public ResponseEntity<EmployeeModel> registCheckIn(@PathVariable Long id){
        EmployeeModel updatedEmployee = employeeService.registCheckIn(id);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/records")
    public ResponseEntity<List <EmployeeModel>> getRecords(){
        try {
            List<EmployeeModel> records = employeeService.getRecords();
            return ResponseEntity.ok(records);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<EmployeeModel> updateCheckOut(@PathVariable Long id) {
        try {
            EmployeeModel employeeModel = employeeService.updateCheckOut(id);
            return ResponseEntity.ok(employeeModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/name") //name
    public ResponseEntity<EmployeeModel> updateName(@PathVariable Long id, @RequestParam String name){
        try {
            EmployeeModel employeeModel = employeeService.updateName(id,name);
            return ResponseEntity.ok(employeeModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/position") //position
    public ResponseEntity<EmployeeModel> updatePosition(@PathVariable Long id, @RequestParam String position){
        try {
            EmployeeModel employeeModel = employeeService.updatePosition(id,position);
            return ResponseEntity.ok(employeeModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/email") //email
    public ResponseEntity<EmployeeModel> updateEmail(@PathVariable Long id, @RequestParam String email){
        try {
            EmployeeModel employeeModel = employeeService.updateEmail(id,email);
            return ResponseEntity.ok(employeeModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/phone") //phone
    public ResponseEntity<EmployeeModel> updatePhone(@PathVariable Long id, @RequestParam String phone){
        try {
            EmployeeModel employeeModel = employeeService.updatePhone(id,phone);
            return ResponseEntity.ok(employeeModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/department") //phone
    public ResponseEntity<EmployeeModel> updateDepartment(@PathVariable Long id, @RequestParam String department){
        try {
            EmployeeModel employeeModel = employeeService.updateDepartment(id,department);
            return ResponseEntity.ok(employeeModel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/record")
    public ResponseEntity<List <EmployeeModel>> getRecordId(@PathVariable Long id){
        List<EmployeeModel> records = employeeService.getRecordId(id);
        return ResponseEntity.ok(records);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        employeeService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
