package com.megan.webp3l.repository;

import com.megan.webp3l.model.Facility;
import com.megan.webp3l.model.Room;
import com.megan.webp3l.model.RoomType;
import com.megan.webp3l.model.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findRoomsByCodeContaining(String code);
    List<Room> findRoomsBySmokingStatus(String smokingStatus);


    @Modifying
    @Query(value = "SELECT * FROM tbl_room R WHERE (R.roomtype_id = :roomType) AND R.room_id NOT IN " +
            "(SELECT RO.room_id FROM tbl_reservation_detail RD JOIN tbl_reservation RS ON RS.reservation_id = RD.reservation_id " +
            "JOIN tbl_room RO ON RO.room_id = RD.room_id WHERE (:startDate < RD.res_start_date AND RD.res_start_date <= :endDate) " +
            "OR (:startDate < RD.res_end_date AND RD.res_end_date <= :endDate) AND RS.branch_id = :branch) " +
            "AND R.room_status = 'AKTIF' AND R.room_id IN (SELECT BR.room_id FROM tbl_branch_room BR " +
            "WHERE BR.branch_id = :branch)", nativeQuery = true)
    List<Room> findAvailableRoom(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                 @Param("roomType") int roomType, @Param("branch") int branch);

    Room findById(int id);

    @Query(value = "SELECT * FROM tbl_room R WHERE (R.roomtype_id LIKE :roomType) AND R.room_id NOT IN " +
            "(SELECT RD.room_id FROM tbl_reservation_detail RD WHERE (:startDate BETWEEN RD.res_start_date AND RD.res_end_date) " +
            "OR (:endDate BETWEEN RD.res_start_date AND RD.res_end_date)) AND R.room_status = 'AKTIF'", nativeQuery = true)
    List<Room> findAvailableRoomByRoomType(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                           @Param("roomType") int roomType);

    @Query(value = "SELECT * FROM tbl_room R WHERE R.room_id NOT IN " +
            "(SELECT RD.room_id FROM tbl_reservation_detail RD WHERE (:startDate BETWEEN RD.res_start_date AND RD.res_end_date) " +
            "OR (:endDate BETWEEN RD.res_start_date AND RD.res_end_date)) AND R.room_status = 'AKTIF' " +
            "AND R.room_id IN (SELECT BR.room_id FROM tbl_branch_room BR WHERE BR.branch_id LIKE :branch)", nativeQuery = true)
    List<Room> findAvailableRoomByBranch(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                           @Param("branch") int branch);

    @Query(value = "SELECT * FROM tbl_room R WHERE R.roomtype_id IN (SELECT FR.roomtype_id FROM tbl_facility_roomtype FR " +
            "WHERE FR.facility_id = :facility)",nativeQuery = true)
    List<Room> findRoomByFacility(@Param("facility") int facility);

    @Query(value = "SELECT * FROM tbl_room R WHERE (R.roomtype_id LIKE :roomType) AND R.room_id NOT IN (SELECT RD.room_id FROM tbl_reservation_detail RD WHERE (RD.res_start_date BETWEEN :startDate AND :endDate) OR (RD.res_end_date BETWEEN :startDate AND :endDate)) AND R.room_status = 'AKTIF' AND R.room_id IN (SELECT BR.room_id FROM tbl_branch_room BR WHERE BR.branch_id LIKE :branch) LIMIT :size", nativeQuery = true)
    List<Room> findAvailableRoomLimit(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                 @Param("roomType") int roomType, @Param("branch") int branch, @Param("size") int size);

    @Query(value = "SELECT R.roomtype_id, RT.roomtype_name, COUNT(*) AS 'quantity' FROM tbl_room R " +
            "JOIN tbl_room_type RT ON R.roomtype_id = RT.roomtype_id " +
            "JOIN tbl_branch_room B ON B.room_id = R.room_id WHERE (R.room_id) NOT IN " +
            "(SELECT RO.room_id FROM tbl_reservation_detail RD JOIN tbl_reservation RS ON RS.reservation_id = RD.reservation_id " +
            "JOIN tbl_room RO ON RO.room_id = RD.room_id " +
            "WHERE (:startDate < RD.res_start_date AND RD.res_start_date <= :endDate) OR (:startDate < RD.res_end_date " +
            "AND RD.res_end_date <= :endDate) AND RS.branch_id = :branch) AND " +
            "R.room_status = 'AKTIF' AND R.room_id IN (SELECT BR.room_id FROM tbl_branch_room BR WHERE BR.branch_id = :branch)" +
            "GROUP BY 1",nativeQuery = true)
    List<RoomType> findAvailableRoomType(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("branch") int branch);
}
