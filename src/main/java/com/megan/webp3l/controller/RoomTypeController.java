package com.megan.webp3l.controller;

import com.megan.webp3l.request.RoomTypeRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/roomtype")
public class RoomTypeController {
    @Autowired
    private RoomTypeService service;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse add(@RequestBody RoomTypeRequest req) {
        return service.add(req);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse update(@RequestBody RoomTypeRequest req, @PathVariable int id)   {
        return service.update(req, id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAll(@Param("name") String name)    {
        return service.showAll(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getOneRoomType(@PathVariable int id)   {
        return service.getOneRoomType(id);
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAvailableRoomType(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                 @Param("branch") int branch)   {
        return service.showAvailableRoomType(startDate, endDate, branch);
    }

    @RequestMapping(value = "/facility", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAvailableRoomTypeFacility(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                 @Param("branch") int branch, @Param("roomType") int roomType) throws ParseException {
        return service.showAvilableRoomTypeFacility(startDate, endDate, branch, roomType);
    }
}
