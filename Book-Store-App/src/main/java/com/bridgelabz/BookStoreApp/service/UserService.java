package com.bridgelabz.BookStoreApp.service;

import com.bridgelabz.BookStoreApp.dto.ChangePasswordDTO;
import com.bridgelabz.BookStoreApp.dto.LoginDTO;
import com.bridgelabz.BookStoreApp.dto.UserDTO;
import com.bridgelabz.BookStoreApp.entity.User;
import com.bridgelabz.BookStoreApp.exception.BookStoreException;
import com.bridgelabz.BookStoreApp.repository.UserRepository;
import com.bridgelabz.BookStoreApp.util.EmailSenderService;
import com.bridgelabz.BookStoreApp.util.TokenUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

//Ability to provide service to controller
@Service
@Slf4j
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtil util;

    //Ability to serve controller's insert user record api call
    public String registerUser(UserDTO userdto) {
        Optional<User> user=userRepo.findByEmail(userdto.getEmail());
        if (!user.isEmpty()) {
            throw new BookStoreException("Email is alredy Register ");
        }
        else {
            User newUser = new User(userdto);
            userRepo.save(newUser);
            String message = newUser.getFullName();
            mailService.sendEmail(userdto.getEmail(), "Account Registration successfully", "Hello" + newUser.getFullName() + " Your Account has been created.");
            return message;
        }
    }
    //Ability to serve controller's user login api call
    public User userLogin(LoginDTO logindto) {
        Optional<User> newUser = userRepo.findByEmail(logindto.getEmail());
        if(logindto.getEmail().equals(newUser.get().getEmail()) && logindto.getPassword().equals(newUser.get().getPassword())) {
            String token = util.createToken(newUser.get().getUserID());
            mailService.sendEmail(logindto.getEmail(),"Account Sign-up successfully","Hello Your Account has been Loggin Successfully.Your token is " + token );
            log.info("SuccessFully Logged In");
            return newUser.get();
        }
        else {

            throw new BookStoreException("User doesn't exists");

        }
    }
    //Ability to serve controller's retrieve user record by token api call
    public User getRecordByToken(String token){
        Integer userIdToken = util.decodeToken(token);
        Optional<User> 	user = userRepo.findById(userIdToken);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            log.info("Record retrieved successfully for given token having id "+userIdToken);
            return user.get();
        }
    }
    //Ability to serve controller's retrieve all user records api call
    public List<User> getAllRecords(){
        List<User> 	userList = userRepo.findAll();
        log.info("All Record Retrieved Successfully");
        return userList;
    }
    //Ability to serve controller's retrieve user record by id api call
    public User getRecord(Integer id){
        Optional<User> 	user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            log.info("Record retrieved successfully for id "+id);
            return user.get();
        }
    }
    //Ability to serve controller's update user record by id api call
    public User updateRecord(Integer id, UserDTO dto) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            User newUser = new User(id,dto);
            userRepo.save(newUser);
            log.info("User data updated successfully");
            return newUser;
        }
    }
    //Ability to serve controller's change password api call
    public User changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
        Optional<User> user = userRepo.findByEmail(dto.getEmail());
        if(user.isEmpty()) {
            throw new BookStoreException("User doesn't exists");
        }
        else {
            if(dto.getEmail().equals(user.get().getEmail())){
                user.get().setPassword(dto.getNewPassword());
                userRepo.save(user.get());
                log.info("Password changes successfully");
                mailService.sendEmail(user.get().getEmail(),"Password Change Successfully"+user.get().getFullName(),"Password is :"+user.get().getPassword());

                return user.get();
            }
            else {
                throw new BookStoreException("Invalid token");
            }
        }
    }
    //Created to serve controller's retrieve user record by email api call
    public User getUserByEmailId(String email) {
        Optional<User> newUser = userRepo.findByEmail(email);
        if (newUser.isEmpty()) {
            throw new BookStoreException("User record does not exist");
        } else {
            return newUser.get();
        }
    }
    public String deleteUserByToken(String token) {
        Integer userIdToken = util.decodeToken(token);
        Optional<User> 	user = userRepo.findById(userIdToken);
        if(user.isEmpty()) {
            throw new BookStoreException("User Record doesn't exists");
        }
        else {
            userRepo.deleteById(user.get().getUserID());
            log.info("Record Delete successfully for given token having id "+userIdToken);
            return "Record Delete successfully for given token id is "+token ;
        }

    }
}