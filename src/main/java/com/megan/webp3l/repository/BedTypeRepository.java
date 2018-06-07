package com.megan.webp3l.repository;

import com.megan.webp3l.model.BedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedTypeRepository extends JpaRepository<BedType, Integer> {
    BedType findById(int id);
}
