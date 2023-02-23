package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.AdminDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_details")
public class AdminUserData {



        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "admin_id")
        private Integer userId;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String password;


        public AdminUserData(Integer userId, AdminDTO adminDTO) {
            this.userId = userId;
            this.firstName = adminDTO.firstName;
            this.lastName = adminDTO.lastName;
            this.phoneNumber = adminDTO.phoneNumber;
            this.email = adminDTO.email;
            this.password = adminDTO.password;
        }

    public AdminUserData(AdminDTO adminDTO) {
        this.userId = userId;
        this.firstName = adminDTO.firstName;
        this.lastName = adminDTO.lastName;
        this.phoneNumber = adminDTO.phoneNumber;
        this.email = adminDTO.email;
        this.password = adminDTO.password;
    }


    public void updateAdminUserData(AdminDTO adminDTO) {
            this.firstName = adminDTO.firstName;
            this.lastName = adminDTO.lastName;
            this.phoneNumber = adminDTO.phoneNumber;
            this.email = adminDTO.email;
            this.password = adminDTO.password;
        }
    }


