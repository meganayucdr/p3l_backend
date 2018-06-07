package com.megan.webp3l.repository;

import com.megan.webp3l.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    Facility findFacilityById(int id);
    List<Facility> findFacilitiesByNameContaining(String name);
    List<Facility> findFacilitiesByIdIn(List<Integer> id);
    List<Facility> findFacilitiesByStatus(String status);
    Facility findByName(String name);
    boolean existsByName(String name);
}
