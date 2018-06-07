package com.megan.webp3l.service;

import com.megan.webp3l.request.FacilityRequest;
import com.megan.webp3l.response.DefaultResponse;

public interface FacilityService {
    DefaultResponse add(FacilityRequest req);
    DefaultResponse update(FacilityRequest req, int id);
    DefaultResponse showAll(String name);
    DefaultResponse showActiveFacilities();
}
