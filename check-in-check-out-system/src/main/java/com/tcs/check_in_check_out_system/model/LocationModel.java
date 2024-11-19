package com.tcs.check_in_check_out_system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name ="location")
public class LocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="location_id")
    private Long location;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("employee-location")
    private EmployeeModel employeeId;

    @ManyToOne
    @JoinColumn(name = "check_id")
    @JsonIgnoreProperties("check-location")
    private CheckInModel check;
//    @JsonIgnoreProperties("locationModels")

}
