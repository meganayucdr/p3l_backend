package com.megan.webp3l.repository;

import com.megan.webp3l.model.Reservation;
import com.megan.webp3l.model.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
    @Query(value = "SELECT SUM(RD.resdet_subtotal) FROM tbl_reservation_detail RD WHERE RD.reservation_id = :id", nativeQuery = true)
    double totalReservation(@Param("id") String id);

    List<ReservationDetail> findReservationDetailsByReservation(Reservation reservation);
}
