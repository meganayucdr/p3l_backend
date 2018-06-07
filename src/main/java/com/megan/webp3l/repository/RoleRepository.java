package com.megan.webp3l.repository;

import com.megan.webp3l.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleById(int id);
    List<Role> findRolesByIdGreaterThan(int id);
    List<Role> findRolesByIdLessThan(int id);
}
