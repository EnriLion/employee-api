package com.tcs.check_in_check_out_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    private String position;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //Set cascade to manage check-ins
    @JsonManagedReference
    private List<CheckInModel> checkIns = new ArrayList<>();

//    //Getter & Setters
//
//    public Long getId(){
//        return id;
//    }
//
//    public void setId(Long id){
//       this.id = id;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public LocalDateTime getCheckInTime(){
//        return checkInTime;
//    }
//
//    public void setCheckInTime(LocalDateTime checkInTime){
//        this.checkInTime = checkInTime;
//    }
//
//    public LocalDateTime getCheckOutTime(){
//        return checkOutTime;
//    }
//
//    public void setCheckOutTime(LocalDateTime checkOutTime){
//        this.checkOutTime = checkOutTime;
//    }
//
//    public String getDepartment(){
//        return department;
//    }
//
//    public void setDepartment(String department){
//        this.department = department;
//    }
//
//    public String getPosition(){
//        return position;
//    }
//
//    public void setPosition(String position){
//        this.position = position;
//    }
//
//    public String getEmail(){
//        return email;
//    }
//
//    public void setEmail(String email){
//        this.email = email;
//    }
//
//    public String getPhone(){
//        return phone;
//    }
//
//    public void setPhone(String phone){
//        this.phone = phone;
//    }
//

//   public List<CheckInModel> getCheckIns(){
//        return  checkIns;
//    }
//    public void setCheckIns(List<CheckInModel> checkIns){
//        this.checkIns = checkIns;
//    }

}
