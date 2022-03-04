package com.betepawa.pawaTask2.repository;

import com.betepawa.pawaTask2.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks,Long> {

}