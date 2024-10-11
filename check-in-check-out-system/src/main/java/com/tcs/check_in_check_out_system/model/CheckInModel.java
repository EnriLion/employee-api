package com.tcs.check_in_check_out_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "check_in")
public class CheckInModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkInId;

  /*  @Column(name = "person")
    private String person;*/

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    @Column(name = "status")
    private Boolean status = false;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private EmployeeModel employee;

//    public String getPerson(){
//       return  person;
//    }
//
//    public void setPerson(String person){
//        this.person = person;
//    }
//
//    public LocalDateTime getCheckInTime(){
//        return  checkInTime;
//    }
//
//    public void setCheckInTime(LocalDateTime checkInTime){
//        this.checkInTime = checkInTime;
//    }
//
//    public LocalDateTime getCheckOutTime(){
//      return  checkOutTime;
//    }
//
//    public void setCheckOutTime(LocalDateTime checkOutTime){
//        this.checkOutTime = checkOutTime;
//    }
//
//    public Boolean getStatus(){
//        return status;
//    }
//
//    public void setStatus(Boolean status){
//        this.status = status;
//    }
//
//    public EmployeeModel getEmployee(){
//        return  employee;
//    }
//
//    public void setEmployee(EmployeeModel employee){
//        this.employee = employee;
//    }
//
}
