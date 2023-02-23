package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.*;
import com.bridgelabz.bookstoreapp.model.AdminUserData;
import com.bridgelabz.bookstoreapp.model.UserData;

import java.util.List;

public interface AdminUserServiceImpl {

    AdminUserData updateUserData(Integer id, AdminDTO adminDTO);


    ResponseDTO loginUser(AdminLoginDTO adminLoginDTO);


    AdminUserData  registerUser(AdminDTO adminDTO);


}
