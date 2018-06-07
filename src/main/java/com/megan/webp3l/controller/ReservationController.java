package com.megan.webp3l.controller;

import com.megan.webp3l.request.GuestReservationRequest;
import com.megan.webp3l.request.MemberReservationRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService service;

    @RequestMapping(value = "/guest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse addReservationGuest(@RequestBody GuestReservationRequest req) {
        return service.addGuestReservation(req);
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse addReservationMember(@RequestBody MemberReservationRequest req) {
        return service.addMemberReservation(req);
    }

    @RequestMapping(value = "/receipt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showReceipt(@Param("id") String id) {
        return service.receipt(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showReservationByType(@Param("type") String type) {
        return service.showReservationByType(type);
    }
}
