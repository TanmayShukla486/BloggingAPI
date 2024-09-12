package com.example.trial_blog.service.interfaces;

import com.example.trial_blog.custom_exception.AlreadyLikeBlogException;
import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.LikeNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import jakarta.transaction.Transactional;

public interface LikeServiceInterface {


    @Transactional
    int addLike(long blogId) throws BlogNotFoundException, AlreadyLikeBlogException, UserNotFoundException;
    @Transactional
    int removeLike(long blogId) throws LikeNotFoundException, BlogNotFoundException;
    int getAllLikes(long blogId) throws BlogNotFoundException;
}
