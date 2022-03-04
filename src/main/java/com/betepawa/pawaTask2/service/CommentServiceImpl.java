package com.betepawa.pawaTask2.service;

import com.betepawa.pawaTask2.exception.ResourceNotFoundException;
import com.betepawa.pawaTask2.models.Comment;
import com.betepawa.pawaTask2.models.Tasks;
import com.betepawa.pawaTask2.repository.CommentRepository;
import com.betepawa.pawaTask2.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private TaskRepository taskRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Override

    public Comment update(Comment comment) {
        if (!commentRepository.existsById(comment.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not updated");
        }

        return commentRepository.save(comment);
    }

    @Override
    public Tasks save(Long taskId, Comment comment) {

        Tasks task = taskRepository.findById(taskId).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id;" + taskId));

        comment.setDate(Date.from(Instant.now()));

        task.addComment(comment);
        taskRepository.saveAndFlush(task);

        return task;
    }

    @Override
    public void deleteById(Long id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tsk to be deleted not found with id :" + id));
        commentRepository.delete(existingComment);
    }
}
