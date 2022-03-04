package com.betepawa.pawaTask2.controller;

import com.betepawa.pawaTask2.models.Comment;
import com.betepawa.pawaTask2.models.Tasks;
import com.betepawa.pawaTask2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("pawa/api/comments")
public class CommentController {
    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PutMapping("")
    public Comment updateComment(@RequestBody Comment comment) {
        commentService.update(comment);

        return comment;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {

        commentService.deleteById(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{id}")
    public Tasks addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.save(id, comment);
    }
}
