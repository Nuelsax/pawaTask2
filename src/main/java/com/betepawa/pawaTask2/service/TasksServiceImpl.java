package com.betepawa.pawaTask2.service;

import com.betepawa.pawaTask2.exception.ResourceNotFoundException;
import com.betepawa.pawaTask2.models.Comment;
import com.betepawa.pawaTask2.models.Tasks;
import com.betepawa.pawaTask2.models.User;
import com.betepawa.pawaTask2.repository.TaskRepository;
import com.betepawa.pawaTask2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TasksServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }




    @Override
    public List<Tasks> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Tasks findById(Long id) {
        Tasks task  = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id;" + id));
        return task;
    }

    @Override
    public Tasks save(Tasks task, Long userId) {
        task.setDate(new Date());
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("no user found"));
        task.setCreatedBy(user.getFirstName() + ' ' + user.getLastName());
        task.setUser(user);

        List<Comment> comments = task.getComments();

        for(Comment val : comments) {
            val.setDate(new Date());
        }

        return taskRepository.save(task);
    }

    @Override
    public Tasks update(Tasks task,Long taskId) {
        Tasks taskToUpdate  = taskRepository.findById(taskId).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id;" +taskId));

        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDay(task.getDay());
        taskToUpdate.setDate(new Date());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setMonth(task.getMonth());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setYear(task.getYear());


        return taskRepository.save(taskToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Tasks existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tsk to be deleted not found with id :" + id));
        taskRepository.delete(existingTask);
    }
}