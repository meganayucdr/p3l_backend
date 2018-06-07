package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.Room;
import com.megan.webp3l.repository.BedTypeRepository;
import com.megan.webp3l.repository.BranchRepository;
import com.megan.webp3l.repository.RoomRepository;
import com.megan.webp3l.repository.RoomTypeRepository;
import com.megan.webp3l.repository.jdbc.RoomJdbc;
import com.megan.webp3l.request.RoomRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RoomRepository repository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private BedTypeRepository bedTypeRepository;
    @Autowired
    private RoomJdbc roomJdbc;

    @Override
    public DefaultResponse add(RoomRequest req) {
        Room room = Room.builder()
                .code(generateCode(req))
                .number(req.getNumber())
                .floor(req.getFloor())
                .smokingStatus(req.getSmokingStatus())
                .status("AKTIF")
                .bedType(bedTypeRepository.findById(req.getBedType()))
                .roomType(roomTypeRepository.findRoomTypeById(req.getRoomType()))
                .branchList(branchRepository.findBranchesByIdIn(req.getBranchList()))
                .build();

        return DefaultResponse.builder()
                .data(repository.save(room))
                .message("Data berhasil disimpan!")
                .build();
    }

    @Override
    public DefaultResponse update(RoomRequest req, int id) {
        if (repository.existsById(id))  {
            Room room = Room.builder()
                    .id(id)
                    .code(generateCode(req))
                    .number(req.getNumber())
                    .floor(req.getFloor())
                    .smokingStatus(req.getSmokingStatus())
                    .status(req.getStatus())
                    .bedType(bedTypeRepository.findById(req.getBedType()))
                    .roomType(roomTypeRepository.findRoomTypeById(req.getRoomType()))
                    .branchList(branchRepository.findBranchesByIdIn(req.getBranchList()))
                    .build();

            return DefaultResponse.builder()
                    .data(repository.save(room))
                    .message("Data berhasil disimpan!")
                    .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Data tidak ditemukan!")
                    .build();
    }

    @Override
    public DefaultResponse showAll(String code) {
        return DefaultResponse.builder()
                .data(repository.findRoomsByCodeContaining(code))
                .build();
    }

    @Override
    public DefaultResponse getOneRoom(int id) {
        if (repository.existsById(id))  {
            return DefaultResponse.builder()
                    .data(repository.findById(id))
                    .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Data tidak ditemukan!")
                    .build();
    }

    @Override
    public String generateCode(RoomRequest req) {
        String roomNumb = String.format("%03d",req.getNumber());
        String floorNumb = Integer.toString(req.getFloor());
        String code;
        if (roomTypeRepository.findRoomTypeById(req.getRoomType()).getName().equalsIgnoreCase("Superior")) {
            code = floorNumb+roomNumb+"SP";
        }
        else
            if (roomTypeRepository.findRoomTypeById(req.getRoomType()).getName().equalsIgnoreCase("Double Deluxe")){
                code = floorNumb+roomNumb+"DD";
            }
            else
                if (roomTypeRepository.findRoomTypeById(req.getRoomType()).getName().equalsIgnoreCase("Executive Deluxe")) {
                    code = floorNumb+roomNumb+"ED";
                }
                else
                    code = floorNumb+roomNumb+"JS";
        return code;
    }

    @Override
    public DefaultResponse showAllBedTypes() {
        return DefaultResponse.builder()
                .data(bedTypeRepository.findAll())
                .build();
    }

    @Override
    public DefaultResponse showAvailableRoom(String startDate, String endDate, int roomType, int branch) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return DefaultResponse.builder()
                .data(repository.findAvailableRoom(start, end, roomType, branch))
                .build();
    }

    @Override
    public DefaultResponse showAvailableRoomByRoomType(String startDate, String endDate, int roomType) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return DefaultResponse.builder()
                .data(repository.findAvailableRoomByRoomType(start, end, roomType))
                .build();
    }

    @Override
    public DefaultResponse showAvailableRoomByBranch(String startDate, String endDate, int branch) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return DefaultResponse.builder()
                .data(repository.findAvailableRoomByBranch(start, end, branch))
                .build();
    }

    @Override
    public DefaultResponse showRoomByFacility(int facility) {
        return DefaultResponse.builder()
                .data(repository.findRoomByFacility(facility))
                .build();
    }
}
