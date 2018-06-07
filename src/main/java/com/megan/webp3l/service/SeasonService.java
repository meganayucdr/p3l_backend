package com.megan.webp3l.service;

import com.megan.webp3l.request.SeasonRequest;
import com.megan.webp3l.response.DefaultResponse;

public interface SeasonService {
    DefaultResponse add(SeasonRequest req);
    DefaultResponse update(SeasonRequest req, int id);
    DefaultResponse showAll(String name);
}
