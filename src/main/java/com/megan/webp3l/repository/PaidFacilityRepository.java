package com.megan.webp3l.repository;

import com.megan.webp3l.model.PaidFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidFacilityRepository extends JpaRepository<PaidFacility, Integer> {
    PaidFacility findPaidFacilityById(int id);
    List<PaidFacility> findPaidFacilityByNameContaining(String name);
    boolean existsByName(String name);
    PaidFacility findByName(String name);
}
