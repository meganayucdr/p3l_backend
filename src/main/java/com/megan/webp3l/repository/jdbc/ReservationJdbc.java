package com.megan.webp3l.repository.jdbc;

import com.megan.webp3l.response.RoomTypeReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationJdbc {
    @Autowired
    private ManageConnection manageConnection;

    public List<RoomTypeReceipt> roomTypeReceipts(String reservationId) {
        List<RoomTypeReceipt> list = new ArrayList<>();
        String SQL = "SELECT RD.price_perroom AS 'price', COUNT(RD.roomtype_id) AS 'quantity', " +
                "RT.roomtype_name AS 'roomtype', BT.bedtype_name AS 'bedtype', SUM(RD.resdet_subtotal) AS 'subtotal'," +
                "RD.res_start_date AS 'checkIn', RD.res_end_date AS 'checkOut' " +
                "FROM tbl_reservation_detail RD " +
                "JOIN tbl_room_type RT ON RT.roomtype_id = RD.roomtype_id " +
                "JOIN tbl_room R ON R.room_id = RD.room_id " +
                "JOIN tbl_bed_type BT ON BT.bedtype_id = R.bedtype_id " +
                "WHERE RD.reservation_id LIKE ?" +
                "GROUP BY RD.roomtype_id";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, reservationId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                RoomTypeReceipt roomTypeReceipt = RoomTypeReceipt.builder()
                        .roomType(resultSet.getString("roomtype"))
                        .bedType(resultSet.getString("bedtype"))
                        .price(resultSet.getDouble("price"))
                        .quantity(resultSet.getInt("quantity"))
                        .subTotal(resultSet.getDouble("subtotal"))
                        .checkIn(resultSet.getDate("checkIn"))
                        .checkOut(resultSet.getDate("checkOut"))
                        .build();
                list.add(roomTypeReceipt);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
