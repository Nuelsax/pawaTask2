package com.betepawa.pawaTask2.service;

import com.betepawa.pawaTask2.models.Comment;
import com.betepawa.pawaTask2.models.Tasks;

public interface CommentService {
    Comment update(Comment comment);

    Tasks save(Long taskId, Comment comment);

    void deleteById(Long id);
}