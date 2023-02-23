package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.*;
import com.bridgelabz.bookstoreapp.model.AdminUserData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.service.AdminUserServiceImpl;
import com.bridgelabz.bookstoreapp.service.EmailSenderService;
import com.bridgelabz.bookstoreapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin( allowedHeaders = "*", origins = "*")
@RequestMapping("/bookstoreadmin")
@RestController
public class AdminUserController {

    @Autowired
    private AdminUserServiceImpl adminUserService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> adminRegistration(@Valid @RequestBody AdminDTO adminDTO) {
        AdminUserData userData = adminUserService.registerUser(adminDTO);
        ResponseDTO respDTO = new ResponseDTO("Admin created successfully.....",userData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{adminId}")
    public ResponseEntity<ResponseDTO> updateUserData(@PathVariable("adminId") Integer userId, @Valid @RequestBody AdminDTO adminDTO) {
        AdminUserData adminUserData = adminUserService.updateUserData(userId, adminDTO);
        ResponseDTO respDTO = new ResponseDTO("User Updated Successfully", adminUserData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody AdminLoginDTO adminLoginDTO) {
        ResponseDTO respDTO = adminUserService.loginUser(adminLoginDTO);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }
}