package com.megan.webp3l.service;

import com.megan.webp3l.request.UserRequest;
import com.megan.webp3l.response.DefaultResponse;

public interface UserService {
    DefaultResponse addCustomer(UserRequest req);
    DefaultResponse addEmployee(UserRequest req);
    DefaultResponse addGuest(UserRequest req);
    DefaultResponse updateCustomer(UserRequest req, int id);
    DefaultResponse updateEmployee(UserRequest req, int id);
    DefaultResponse getAllCustomer(String name);
    DefaultResponse getAllEmployee(String name);
    DefaultResponse getOneUser(int id);
    DefaultResponse getRoles();
    DefaultResponse getEmployeeRoles();
}
