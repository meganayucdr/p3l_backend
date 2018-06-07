package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.model.RoomType;
import com.megan.webp3l.repository.FacilityRepository;
import com.megan.webp3l.repository.RoomRepository;
import com.megan.webp3l.repository.RoomTypeRepository;
import com.megan.webp3l.repository.jdbc.RoomJdbc;
import com.megan.webp3l.request.RoomTypeRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeRepository repository;
    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomJdbc roomJdbc;

    @Override
    public DefaultResponse add(RoomTypeRequest req) {
        RoomType roomType = RoomType.builder()
                .name(req.getName())
                .price(req.getPrice())
                .desc(req.getDesc())
                .capacity(req.getCapacity())
                .pict(req.getPict())
                .facilityList(facilityRepository.findFacilitiesByIdIn(req.getFacilityList()))
                .status("AKTIF")
                .build();
        return DefaultResponse.builder()
                .data(repository.save(roomType))
                .message("Data berhasil disimpan!")
                .build();
    }

    @Override
    public DefaultResponse update(RoomTypeRequest req, int id) {
        if (repository.existsById(id))  {
            RoomType roomType = RoomType.builder()
                    .id(id)
                    .name(req.getName())
                    .price(req.getPrice())
                    .desc(req.getDesc())
                    .capacity(req.getCapacity())
                    .pict(req.getPict())
                    .facilityList(facilityRepository.findFacilitiesByIdIn(req.getFacilityList()))
                    .status(req.getStatus())
                    .build();
            return DefaultResponse.builder()
                    .data(repository.save(roomType))
                    .message("Data berhasil disimpan!")
                    .build();
        }
        else
            return DefaultResponse.builder()
                .message("Data tidak ditemukan!")
                .build();
    }

    @Override
    public DefaultResponse showAll(String name) {
        return DefaultResponse.builder()
                .data(repository.findRoomTypesByNameContaining(name))
                .build();
    }

    @Override
    public DefaultResponse getOneRoomType(int id) {
        if(repository.existsById(id))   {
            return DefaultResponse.builder()
                    .data(repository.findById(id))
                    .build();
        }
        else
            return DefaultResponse.builder()
                .message("Data tidak ditemukan")
                .build();

    }

    @Override
    public DefaultResponse showAvailableRoomType(String startDate, String endDate, int branch) {
        return DefaultResponse.builder()
                .data(roomJdbc.availableRoomTypes(startDate, endDate, branch))
                .build();
    }

    @Override
    public DefaultResponse showAvilableRoomTypeFacility(String startDate, String endDate, int branch, int roomtype) throws ParseException {
        return DefaultResponse.builder()
                .data(roomJdbc.availableRoomTypeFacilities(startDate, endDate, branch, roomtype))
                .build();
    }
}
