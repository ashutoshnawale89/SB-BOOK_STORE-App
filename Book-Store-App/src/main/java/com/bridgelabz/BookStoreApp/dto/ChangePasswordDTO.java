package com.bridgelabz.BookStoreApp.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String email;
    private String newPassword;
    private String token;

}