package com.megan.webp3l.service;

import com.megan.webp3l.request.RoomTypeRequest;
import com.megan.webp3l.response.DefaultResponse;

import java.text.ParseException;

public interface RoomTypeService {
    DefaultResponse add(RoomTypeRequest req);
    DefaultResponse update(RoomTypeRequest req, int id);
    DefaultResponse showAll(String name);
    DefaultResponse getOneRoomType(int id);
    DefaultResponse showAvailableRoomType(String startDate, String endDate, int branch);
    DefaultResponse showAvilableRoomTypeFacility(String startDate, String endDate, int branch, int roomtype) throws ParseException;
}
