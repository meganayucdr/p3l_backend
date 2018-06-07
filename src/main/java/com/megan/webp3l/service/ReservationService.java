package com.megan.webp3l.service;

import com.megan.webp3l.model.Reservation;
import com.megan.webp3l.model.ReservationDetail;
import com.megan.webp3l.model.Room;
import com.megan.webp3l.request.GuestReservationRequest;
import com.megan.webp3l.request.MemberReservationRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.response.Receipt;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    DefaultResponse addGuestReservation(GuestReservationRequest request);
    String generateCode(Date date, String type);
    List<ReservationDetail> addDetail(Reservation reservation, GuestReservationRequest request);
    double sumTotal(List<ReservationDetail> details);
    List<Room> addRoomToReservation(Date startDate, Date endDate, int roomType, int branch, int size);
    DefaultResponse addMemberReservation(MemberReservationRequest request);
    List<ReservationDetail> addDetailMember(Reservation reservation, MemberReservationRequest request);
    DefaultResponse receipt(String reservationId);
    DefaultResponse showReservationByUser(int id);
    DefaultResponse showReservationByType(String type);
}
