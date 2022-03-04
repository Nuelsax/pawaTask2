package com.betepawa.pawaTask2.controller;

import com.betepawa.pawaTask2.models.Tasks;
import com.betepawa.pawaTask2.models.User;
import com.betepawa.pawaTask2.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("pawa/api/tasks")
public class TaskController {

    private TasksService taskService;
    @Autowired
    public TaskController(TasksService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<Tasks> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Tasks getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/{id}")
    public Tasks addTask(@RequestBody Tasks task, @PathVariable Long id) {
        System.out.println("this is is"+ id);
        System.out.println(task);

        return taskService.save(task, id);
    }

    @PutMapping("/{id}")
    public Tasks updateTask(@PathVariable Long id,@RequestBody Tasks task, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        task.setLastEditedBy(user.getFirstName() + ' ' + user.getLastName());
        taskService.update(task, id);

        return task;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        System.out.println("this is is"+ id);

        taskService.deleteById(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
