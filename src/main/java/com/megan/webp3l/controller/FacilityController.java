package com.megan.webp3l.controller;

import com.megan.webp3l.request.FacilityRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    private FacilityService service;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse add(@RequestBody FacilityRequest req) {
        return service.add(req);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse update(@RequestBody FacilityRequest req, @PathVariable int id)   {
        return service.update(req, id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAll(@Param("name") String name)    {
        return service.showAll(name);
    }

    @RequestMapping(value = "/aktif", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showActiveFacilities()    {
        return service.showActiveFacilities();
    }
}
