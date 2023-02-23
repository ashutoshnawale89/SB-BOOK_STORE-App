package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.AdminDTO;
import com.bridgelabz.bookstoreapp.dto.AdminLoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.AdminUserData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.AdminRegistrationRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AdminUserService implements AdminUserServiceImpl {

    @Autowired
    AdminUserData adminUserData;

    @Autowired
    private AdminRegistrationRepository adminRepository;


    @Override
    public AdminUserData registerUser(AdminDTO adminDTO) {
        System.out.println(adminDTO);
        AdminUserData adminData = adminRepository.findAdminUserDataByEmail(adminDTO.email);
        if (adminData == null) {
            adminUserData =new AdminUserData(adminDTO);
            adminUserData =adminRepository.save(adminUserData);
            System.out.println(adminUserData);
            return adminUserData;
        }
        else {
            throw new BookStoreException("Email already exists",
                    BookStoreException.ExceptionType.USER_ALREADY_PRESENT);
        }
    }
    @Override
    public AdminUserData updateUserData(Integer id, AdminDTO adminDTO) {
        AdminUserData adminData = adminRepository.findAllByUserId(id);
        if (adminData != null) {
            adminUserData =new AdminUserData(id,adminDTO);
            adminUserData =adminRepository.save(adminUserData);
            System.out.println(adminUserData);
            return adminUserData;
        }
        else {
            throw new BookStoreException("Id Does not exists",
                    BookStoreException.ExceptionType.USER_ALREADY_PRESENT);
        }
    }

    @Override
    public ResponseDTO loginUser(AdminLoginDTO adminLoginDTO) {
        System.out.println(adminLoginDTO.getEmail());
        AdminUserData adminData = adminRepository.findAdminUserDataByEmail(adminLoginDTO.getEmail());
        if (adminData == null) {
            throw new BookStoreException("Enter registered Email", BookStoreException.ExceptionType.EMAIL_NOT_FOUND);
        }
        else {
            if(adminData.getPassword().equals(adminLoginDTO.password)){
                return new ResponseDTO("Logged in successfully", adminData.getFirstName()+" "+adminData.getLastName());
            }
            else{
                throw new BookStoreException("Please verify your Password",
                        BookStoreException.ExceptionType.PASSWORD_INVALID);
            }
        }
    }


}