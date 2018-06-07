package com.megan.webp3l.service;

import com.megan.webp3l.request.BranchRequest;
import com.megan.webp3l.response.DefaultResponse;

public interface BranchService {
    DefaultResponse add(BranchRequest req);
    DefaultResponse update(BranchRequest req, int id);
    DefaultResponse showAll(String location);
    DefaultResponse showActiveBranch();
}
