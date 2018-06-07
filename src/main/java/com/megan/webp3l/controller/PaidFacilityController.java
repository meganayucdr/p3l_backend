package com.megan.webp3l.controller;

import com.megan.webp3l.request.PaidFacilityRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.PaidFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paidfacility")
public class PaidFacilityController {
    @Autowired
    private PaidFacilityService service;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse add(@RequestBody PaidFacilityRequest req) {
        return service.add(req);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse update(@RequestBody PaidFacilityRequest req, @PathVariable int id)   {
        return service.update(req, id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAll( @Param("name") String name)    {
        return service.showAll(name);
    }

}
