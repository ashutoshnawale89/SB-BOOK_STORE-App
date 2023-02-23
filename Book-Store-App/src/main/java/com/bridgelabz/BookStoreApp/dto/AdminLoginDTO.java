package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

@Data
public class AdminLoginDTO {
    public String email;
    public String password;

    public AdminLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

