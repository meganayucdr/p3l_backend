package com.megan.webp3l.controller;

import com.megan.webp3l.repository.UserRepository;
import com.megan.webp3l.request.FacilityRequest;
import com.megan.webp3l.request.UserRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.ReservationService;
import com.megan.webp3l.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "/cust/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse addCustomer(@RequestBody UserRequest req) {
        return service.addCustomer(req);
    }

    @RequestMapping(value = "/cust/guest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse addGuest(@RequestBody UserRequest req) {
        return service.addGuest(req);
    }

    @RequestMapping(value = "/employee/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse addEmployee(@RequestBody UserRequest req) {
        return service.addEmployee(req);
    }

    @RequestMapping(value = "/cust/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse updateCustomer(@RequestBody UserRequest req, @PathVariable int id)   {
        return service.updateCustomer(req, id);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse updateEmployee(@RequestBody UserRequest req, @PathVariable int id)   {
        return service.updateEmployee(req, id);
    }

    @RequestMapping(value = "/cust", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAllByRole(@Param("name")  String name) {
        return service.getAllCustomer(name);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAllEmployee(@Param("name")  String name) {
        return service.getAllEmployee(name);
    }

    @RequestMapping(value = "/cust/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getOneUser(@PathVariable int id) {
        return service.getOneUser(id);
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getRole() {
        return service.getRoles();
    }

    @RequestMapping(value = "/roleEmployee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getEmployeeRole() {
        return service.getEmployeeRoles();
    }

    @RequestMapping(value = "/cust/{id}/reservation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showHistory(@PathVariable int id) {
        return reservationService.showReservationByUser(id);
    }
}
