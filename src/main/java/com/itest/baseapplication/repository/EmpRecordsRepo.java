package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.EmpRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpRecordsRepo extends JpaRepository<EmpRecords,String> {
    Optional<EmpRecords> findByUsername ( String username);
    Optional<String> findFullNameByUsername ( String username);
}
