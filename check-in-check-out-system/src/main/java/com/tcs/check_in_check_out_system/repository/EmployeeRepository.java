package com.tcs.check_in_check_out_system.repository;

import com.tcs.check_in_check_out_system.model.CheckInModel;
import com.tcs.check_in_check_out_system.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
}
