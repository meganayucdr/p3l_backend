package com.megan.webp3l.service;

import com.megan.webp3l.request.RoomRequest;
import com.megan.webp3l.response.DefaultResponse;

import java.text.ParseException;
import java.util.Date;

public interface RoomService {
    DefaultResponse add(RoomRequest req);
    DefaultResponse update(RoomRequest req, int id);
    DefaultResponse showAll(String code);
    DefaultResponse getOneRoom(int id);
    String generateCode(RoomRequest req);
    DefaultResponse showAllBedTypes();
    DefaultResponse showAvailableRoom(String startDate, String endDate, int roomType, int branch) throws ParseException;
    DefaultResponse showAvailableRoomByRoomType(String startDate, String endDate, int roomType) throws ParseException;
    DefaultResponse showAvailableRoomByBranch(String startDate, String endDate, int branch) throws ParseException;
    DefaultResponse showRoomByFacility(int facility);
}
