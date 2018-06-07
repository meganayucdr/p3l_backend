package com.megan.webp3l.controller;

import com.megan.webp3l.request.BranchRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService service;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse add(@RequestBody BranchRequest req) {
        return service.add(req);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse update(@RequestBody BranchRequest req, @PathVariable int id)   {
        return service.update(req, id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showAll(@Param("location") String location)    {
        return service.showAll(location);
    }

    @RequestMapping(value = "/aktif", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse showActiveBranch()    {
        return service.showActiveBranch();
    }
}
