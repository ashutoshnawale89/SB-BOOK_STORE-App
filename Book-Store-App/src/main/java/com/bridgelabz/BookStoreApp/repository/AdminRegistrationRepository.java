package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.AdminUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRegistrationRepository extends JpaRepository<AdminUserData, Integer> {

    AdminUserData findAdminUserDataByEmail(String email);

    AdminUserData findAllByUserId(Integer id);
}
