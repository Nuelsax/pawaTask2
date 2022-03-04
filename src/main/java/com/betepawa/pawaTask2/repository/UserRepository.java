package com.betepawa.pawaTask2.repository;

import com.betepawa.pawaTask2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select s from User s where s.email = ?1 and s.password = ?2")
    User getUserByEmailAndPassword(String email, String password);


}