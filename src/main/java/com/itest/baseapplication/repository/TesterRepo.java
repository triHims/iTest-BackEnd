package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.Tester;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterRepo extends JpaRepository<Tester,String> {
	Optional<Tester> findByTesterId ( Long testerId);
}
