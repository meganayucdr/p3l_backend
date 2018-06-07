package com.megan.webp3l.controller;

import com.megan.webp3l.request.RoomRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService service;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse add(@RequestBody RoomRequest req) {
        return service.add(req);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse update(@RequestBody RoomRequest req, @PathVariable int id)   {
        return service.update(req, id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAll(@Param("code") String code)    {
        return service.showAll(code);
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getOneRoom(@PathVariable int id)   {
        return service.getOneRoom(id);    }*/

    @RequestMapping(value = "/bedtype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAllBedTypes()    {
        return service.showAllBedTypes();
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAvailableRoom(@Param("startDate")String startDate, @Param("endDate") String endDate,
                                             @Param("roomType") int roomType, @Param("branch") int branch) throws ParseException {
        return service.showAvailableRoom(startDate, endDate, roomType, branch);
    }

    @RequestMapping(value = "/available/roomtype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAvailableRoomByRoomType(@Param("startDate")String startDate, @Param("endDate") String endDate,
                                             @Param("roomType") int roomType) throws ParseException {
        return service.showAvailableRoomByRoomType(startDate, endDate, roomType);
    }

    @RequestMapping(value = "/available/branch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAvailableRoomByBranch(@Param("startDate")String startDate, @Param("endDate") String endDate,
                                             @Param("branch") int branch) throws ParseException {
        return service.showAvailableRoomByBranch(startDate, endDate, branch);
    }

    @RequestMapping(value = "/facility/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showRoomByFacility(@PathVariable int id)  {
        return service.showRoomByFacility(id);
    }
}
