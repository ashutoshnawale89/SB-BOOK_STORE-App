package com.bridgelabz.BookStoreApp.service;

import com.bridgelabz.BookStoreApp.dto.ChangePasswordDTO;
import com.bridgelabz.BookStoreApp.dto.LoginDTO;
import com.bridgelabz.BookStoreApp.dto.UserDTO;
import com.bridgelabz.BookStoreApp.entity.User;

import java.util.List;

public interface IUserService {
    public String registerUser(UserDTO userdto);

    public User userLogin(LoginDTO logindto);

    public List<User> getAllRecords();

    public User getRecord(Integer id);

    public User getRecordByToken(String token);

    public User updateRecord(Integer id, UserDTO dto);
    public User changePassword(ChangePasswordDTO dto);
    public User getUserByEmailId(String email);
}
