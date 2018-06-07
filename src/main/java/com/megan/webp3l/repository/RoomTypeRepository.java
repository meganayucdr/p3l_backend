package com.megan.webp3l.repository;

import com.megan.webp3l.model.Facility;
import com.megan.webp3l.model.Room;
import com.megan.webp3l.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    List<RoomType> findRoomTypesByNameContaining(String name);
    RoomType findRoomTypeById(int id);
    RoomType findByName(String name);

    @Query(value = "SELECT RT.roomtype_capacity FROM db_sigah.tbl_room_type RT WHERE RT.roomtype_id = :id", nativeQuery = true)
    int getCapacity(@Param("id") int id);

    List<RoomType> findRoomTypesById(List<Integer> id);
}
