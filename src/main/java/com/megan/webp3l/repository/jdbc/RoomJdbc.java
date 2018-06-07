package com.megan.webp3l.repository.jdbc;

import com.megan.webp3l.response.AvailableRoomType;
import com.megan.webp3l.response.AvailableRoomTypeFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomJdbc {
    @Autowired
    private ManageConnection manageConnection;

    public List<AvailableRoomType> availableRoomTypes(String startDate, String endDate, int branch) {
        List<AvailableRoomType> list = new ArrayList<>();
        String SQL = "SELECT R.roomtype_id AS 'id', RT.roomtype_name AS 'name', RT.roomtype_capacity AS 'capacity', " +
                "COUNT(*) AS 'quantity', (RT.roomtype_price * IFNULL((SELECT SE.season_percentage FROM tbl_season SE " +
                "WHERE (STR_TO_DATE(?, '%Y-%m-%d') BETWEEN SE.season_start_date AND SE.season_finish_date) AND SE.branch_id = ? ), 1)) " +
                "AS 'price' FROM tbl_room R " +
                "JOIN tbl_room_type RT ON R.roomtype_id = RT.roomtype_id " +
                "WHERE (R.room_id) NOT IN (SELECT RO.room_id FROM tbl_reservation_detail RD " +
                "JOIN tbl_reservation RS ON RS.reservation_id = RD.reservation_id " +
                "JOIN tbl_room RO ON RO.room_id = RD.room_id " +
                "WHERE (STR_TO_DATE(?, '%Y-%m-%d') < RD.res_start_date AND RD.res_start_date <= STR_TO_DATE(?, '%Y-%m-%d')) " +
                "OR (STR_TO_DATE(?, '%Y-%m-%d') < RD.res_end_date AND RD.res_end_date <= STR_TO_DATE(?, '%Y-%m-%d')) " +
                "AND RS.branch_id = ?) AND R.room_status = 'Aktif' " +
                "AND R.room_id IN (SELECT BR.room_id FROM tbl_branch_room BR WHERE BR.branch_id = ?) GROUP BY 1";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, startDate);
            preparedStatement.setInt(2, branch);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            preparedStatement.setString(5, startDate);
            preparedStatement.setString(6, endDate);
            preparedStatement.setInt(7, branch);
            preparedStatement.setInt(8, branch);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                AvailableRoomType availableRoomType = AvailableRoomType.builder()
                        .roomtype_id(resultSet.getInt("id"))
                        .roomtype_name(resultSet.getString("name"))
                        .quantity(resultSet.getInt("quantity"))
                        .capacity(resultSet.getInt("capacity"))
                        .price(resultSet.getDouble("price"))
                        .build();
                list.add(availableRoomType);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<AvailableRoomTypeFacility> availableRoomTypeFacilities(String startDate, String endDate, int branch, int roomType)  {
        List<AvailableRoomTypeFacility> list = new ArrayList<>();
        String SQL = "SELECT DISTINCT(FR.facility_id) AS 'id', F.facility_name AS 'name' FROM tbl_room R " +
                "JOIN tbl_room_type RT ON RT.roomtype_id = R.roomtype_id " +
                "JOIN tbl_facility_roomtype FR ON FR.roomtype_id = RT.roomtype_id " +
                "JOIN tbl_facility F ON F.facility_id = FR.facility_id " +
                "WHERE (R.roomtype_id = ?) AND R.room_id NOT IN (SELECT RO.room_id FROM tbl_reservation_detail RD " +
                "JOIN tbl_reservation RS ON RS.reservation_id = RD.reservation_id " +
                "JOIN tbl_room RO ON RO.room_id = RD.room_id " +
                "WHERE (STR_TO_DATE(?, '%Y-%m-%d') < RD.res_start_date AND RD.res_start_date <= STR_TO_DATE(?, '%Y-%m-%d')) " +
                "OR (STR_TO_DATE(?, '%Y-%m-%d') < RD.res_end_date AND RD.res_end_date <= STR_TO_DATE(?, '%Y-%m-%d')) AND RS.branch_id = ?) " +
                "AND R.room_status = 'Aktif' AND R.room_id IN " +
                "(SELECT BR.room_id FROM tbl_branch_room BR WHERE BR.branch_id = ?)";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, roomType);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setString(4, startDate);
            preparedStatement.setString(5, endDate);
            preparedStatement.setInt(6, branch);
            preparedStatement.setInt(7, branch);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                AvailableRoomTypeFacility availableRoomTypeFacility = AvailableRoomTypeFacility.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                list.add(availableRoomTypeFacility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }

        return list;
    }
}
