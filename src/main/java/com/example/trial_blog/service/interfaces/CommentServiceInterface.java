package com.example.trial_blog.service.interfaces;

import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.CommentNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.dto.CommentInputDTO;
import com.example.trial_blog.entity.dto.CommentOutputDTO;

import java.util.List;

public interface CommentServiceInterface {

    CommentOutputDTO addComment(CommentInputDTO input) throws BlogNotFoundException, UserNotFoundException;

    List<CommentOutputDTO> getComments(long blogId) throws BlogNotFoundException;

    List<CommentOutputDTO> getCommentsByUser(String username) throws UserNotFoundException;

    String deleteComment(long commentId) throws CommentNotFoundException;
}
