package com.megan.webp3l.repository;

import com.megan.webp3l.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Branch findBranchById(int id);
    boolean existsByLocation(String location);
    List<Branch> findBranchesByLocationContaining(String location);
    List<Branch> findBranchesByIdIn(List<Integer> id);
    Branch findByLocation(String location);
    List<Branch> findBranchesByStatus(String status);
}
