package com.megan.webp3l.repository;

import com.megan.webp3l.model.Role;
import com.megan.webp3l.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
    User findUserByEmailAndPassword(String email, String password);
    List<User> findUsersByRoleIsBetweenAndNameContaining(Role role1, Role role2, String name);
    List<User> findUsersByRoleIsNotAndRoleIsNotAndNameContaining(Role role1, Role role2, String name);

    boolean existsByEmailAndRoleNotIn(String email, List<Role> roles);
    boolean existsByIdentityNumbAndRoleNotIn(String identity, List<Role> roles);
    boolean existsByEmailAndRole(String email, Role role);
    boolean existsByIdentityNumbAndRole(String identity, Role role);

    User findUserByEmailAndRoleNotIn(String email, List<Role> roles);
    User findByEmailAndRole(String email, Role role);

    boolean existsByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM tbl_user U " +
            "JOIN tbl_reservation R ON R.customer_id = U.user_id " +
            "WHERE R.reservation_id LIKE :id AND (U.role_id = 6 OR U.role_id = 7)",nativeQuery = true)
    User findUserByReservation(@Param("id") String id);
}
