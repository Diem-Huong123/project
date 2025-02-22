package com.programmingtechie.repository;


import com.programmingtechie.entity.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, String> {
    Optional<StudentAccount> findByMssv(String mssv);
    Boolean existsByMssv(String mssv);
}
