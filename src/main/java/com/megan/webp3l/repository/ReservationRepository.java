package com.megan.webp3l.repository;

import com.megan.webp3l.model.Reservation;
import com.megan.webp3l.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    Reservation findReservationById(String id);

    @Query(value = "SELECT nextval('my_sequence')" ,nativeQuery = true )
    int getSequenceValue();

    @Query(value = "SELECT hibernate_sequence.next_val FROM hibernate_sequence", nativeQuery = true)
    int getHibValue();

    @Query(value = "SELECT * FROM tbl_reservation WHERE customer_id = :customer", nativeQuery = true)
    List<Reservation> findReservationsByCustomer(@Param("customer") int customer);

    List<Reservation> findReservationsByType(String type);
}
