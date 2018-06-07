package com.megan.webp3l.service;

import com.megan.webp3l.request.PaidFacilityRequest;
import com.megan.webp3l.response.DefaultResponse;

public interface PaidFacilityService {
    DefaultResponse add(PaidFacilityRequest req);
    DefaultResponse update(PaidFacilityRequest req, int id);
    DefaultResponse showAll(String name);
}
