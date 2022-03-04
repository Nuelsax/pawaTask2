package com.betepawa.pawaTask2.service;

import com.betepawa.pawaTask2.models.Tasks;

import java.util.List;

public interface TasksService {

    List<Tasks> findAll();

    Tasks findById(Long id);

    Tasks save(Tasks task, Long userId);

    Tasks update(Tasks task, Long taskId);

    void deleteById(Long id);


}