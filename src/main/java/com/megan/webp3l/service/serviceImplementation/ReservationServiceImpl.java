package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.*;
import com.megan.webp3l.repository.*;
import com.megan.webp3l.repository.jdbc.ReservationJdbc;
import com.megan.webp3l.request.GuestReservationRequest;
import com.megan.webp3l.request.MemberReservationRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.response.Receipt;
import com.megan.webp3l.response.RoomTypeReceipt;
import com.megan.webp3l.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ReservationDetailRepository reservationDetailRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private ReservationJdbc reservationJdbc;

    @Override
    public DefaultResponse addGuestReservation(GuestReservationRequest request) {
        Date date = new Date();
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .identityNumb(request.getIdentityNumb())
                .telp_numb(request.getTelp_numb())
                .instance_name("-")
                .role(roleRepository.findRoleById(7))
                .status("AKTIF")
                .build();

        Reservation reservation = Reservation.builder()
                .id(generateCode(date, request.getType()))
                .type(request.getType())
                .child(request.getChild())
                .adult(request.getAdult())
                .date(date)
                .customer_id(user)
                .branch(branchRepository.findBranchById(request.getBranch()))
                .status("AKTIF")
                .build();

        List<ReservationDetail> details = addDetail(reservation, request);
        reservation.setTotal(sumTotal(details));
        return DefaultResponse.builder()
                .data(userRepository.save(user))
                .data(repository.save(reservation))
                .data(reservationDetailRepository.saveAll(details))
                .build();
    }

    @Override
    public String generateCode(Date date, String type)    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String date_code = sdf.format(date);
        String number = String.format("%03d", repository.getSequenceValue());
        return type + date_code + "-" + number;
    }

    @Override
    public List<ReservationDetail> addDetail(Reservation reservation, GuestReservationRequest request)  {
        List<ReservationDetail> details = new ArrayList<>();
        RoomType roomType = new RoomType();
        long diffInMillies = Math.abs(request.getStartDate().getTime() - request.getEndDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        double season;
        int quantity;
        List<Room> rooms;

        if (seasonRepository.getPercentage(request.getStartDate(), request.getBranch()) == null)    {
            season = 1;
        }
        else
            season = seasonRepository.getPercentage(request.getStartDate(), request.getBranch()).getPercentage();

        for (int i = 0; i < request.getRoomTypes().size(); i++) {
            roomType = roomTypeRepository.findRoomTypeById(request.getRoomTypes().get(i).getId());
            double roomTypePrice = roomType.getPrice();
            double pricePerRoom = season * roomTypePrice;
            double subTotal = pricePerRoom * diff;
            quantity = request.getRoomTypes().get(i).getQuantity();
            rooms = roomRepository.findAvailableRoom(request.getStartDate(), request.getEndDate(), roomType.getId(), request.getBranch());
            for (int j = 0; j < quantity; j++) {
                details.add(
                        ReservationDetail.builder()
                                .startDate(request.getStartDate())
                                .endDate(request.getEndDate())
                                .roomType(roomTypeRepository.findRoomTypeById(request.getRoomTypes().get(i).getId()))
                                .pricePerRoom(pricePerRoom)
                                .subtotal(subTotal)
                                .room(rooms.get(j))
                                .reservation(reservation)
                                .build()
                );
            }
            rooms.clear();
        }

        return details;
    }

    @Override
    public double sumTotal(List<ReservationDetail> details)    {
        double sum = 0;
        for (int i = 0; i < details.size(); i++) {
            sum = sum + details.get(i).getSubtotal();
        }
        return sum;
    }

    @Override
    public List<Room> addRoomToReservation(Date startDate, Date endDate, int roomType, int branch, int size) {
        return roomRepository.findAvailableRoomLimit(startDate, endDate, roomType, branch, size);
    }

    @Override
    public DefaultResponse addMemberReservation(MemberReservationRequest request) {
        Date date =  new Date();
        Reservation reservation = Reservation.builder()
                .id(generateCode(date, request.getType()))
                .type(request.getType())
                .child(request.getChild())
                .adult(request.getAdult())
                .date(date)
                .customer_id(userRepository.findUserById(request.getCustomer_id()))
                .branch(branchRepository.findBranchById(request.getBranch()))
                .status("AKTIF")
                .build();
        List<ReservationDetail> details = addDetailMember(reservation, request);
        reservation.setTotal(sumTotal(details));

        return DefaultResponse.builder()
                .data(repository.save(reservation))
                .data(reservationDetailRepository.saveAll(details))
                .build();
    }

    @Override
    public List<ReservationDetail> addDetailMember(Reservation reservation, MemberReservationRequest request) {
        List<ReservationDetail> details = new ArrayList<>();
        RoomType roomType = new RoomType();
        long diffInMillies = Math.abs(request.getStartDate().getTime() - request.getEndDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        double season;
        int quantity;
        List<Room> rooms;

        if (seasonRepository.getPercentage(request.getStartDate(), request.getBranch()) == null)    {
            season = 1;
        }
        else
            season = seasonRepository.getPercentage(request.getStartDate(), request.getBranch()).getPercentage();

        for (int i = 0; i < request.getRoomTypes().size(); i++) {
            roomType = roomTypeRepository.findRoomTypeById(request.getRoomTypes().get(i).getId());
            double roomTypePrice = roomType.getPrice();
            double pricePerRoom = season * roomTypePrice;
            double subTotal = pricePerRoom * diff;
            quantity = request.getRoomTypes().get(i).getQuantity();
            rooms = roomRepository.findAvailableRoom(request.getStartDate(), request.getEndDate(), roomType.getId(), request.getBranch());
            for (int j = 0; j < quantity; j++) {
                details.add(
                        ReservationDetail.builder()
                                .startDate(request.getStartDate())
                                .endDate(request.getEndDate())
                                .roomType(roomTypeRepository.findRoomTypeById(request.getRoomTypes().get(i).getId()))
                                .pricePerRoom(pricePerRoom)
                                .subtotal(subTotal)
                                .room(rooms.get(j))
                                .reservation(reservation)
                                .build()
                );
            }
            rooms.clear();
        }

        return details;
    }

    @Override
    public DefaultResponse receipt(String reservationId) {
        Reservation reservation = repository.findReservationById(reservationId);
        List<RoomTypeReceipt> list = reservationJdbc.roomTypeReceipts(reservationId);

        User user = userRepository.findUserByReservation(reservationId);
        Receipt receipt = Receipt.builder()
                .reservation_id(reservation.getId())
                .name(user.getName())
                .address(user.getAddress())
                .date(reservation.getDate())
                .adult(reservation.getAdult())
                .child(reservation.getChild())
                .checkOut(list.get(0).getCheckOut())
                .checkIn(list.get(0).getCheckIn())
                .roomTypeReceiptList(list)
                .build();

        return DefaultResponse.builder()
                .data(receipt)
                .build();
    }

    @Override
    public DefaultResponse showReservationByUser(int id) {
        return DefaultResponse.builder()
                .data(repository.findReservationsByCustomer(id))
                .build();
    }

    @Override
    public DefaultResponse showReservationByType(String type) {
        return DefaultResponse.builder()
                .data(repository.findReservationsByType(type))
                .build();
    }


}
