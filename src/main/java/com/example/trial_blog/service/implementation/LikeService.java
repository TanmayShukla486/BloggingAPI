package com.example.trial_blog.service.implementation;

import com.example.trial_blog.custom_exception.AlreadyLikeBlogException;
import com.example.trial_blog.custom_exception.BlogNotFoundException;
import com.example.trial_blog.custom_exception.LikeNotFoundException;
import com.example.trial_blog.custom_exception.UserNotFoundException;
import com.example.trial_blog.entity.blog_entity.Blog;
import com.example.trial_blog.entity.blog_entity.BlogLike;
import com.example.trial_blog.entity.user_entity.User;
import com.example.trial_blog.repository.BlogRepository;
import com.example.trial_blog.repository.LikeRepository;
import com.example.trial_blog.repository.UserRepository;
import com.example.trial_blog.service.interfaces.LikeServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements LikeServiceInterface {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(BlogRepository blogRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    @Transactional
    @Override
    public int addLike(long blogId) throws BlogNotFoundException, AlreadyLikeBlogException, UserNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new BlogNotFoundException("Blog with id="+blogId + " not found")
        );
        if (likeRepository.existsByUserAndBlog(user, blog))
            throw new AlreadyLikeBlogException("Blog already like by user");
        BlogLike like = new BlogLike();
        like.setUser(user); like.setBlog(blog);
        likeRepository.save(like);
        return likeRepository.countByBlog(blog);
    }

    @Override
    @Transactional
    public int removeLike(long blogId) throws LikeNotFoundException, BlogNotFoundException, UserNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new BlogNotFoundException("Blog with id=" + blogId + " not found")
        );
        BlogLike like = likeRepository.findByUserAndBlog(user, blog).orElseThrow(
                () -> new LikeNotFoundException("Like not found for given blog")
        );
        likeRepository.delete(like);
        return likeRepository.countByBlog(blog);
    }

    @Override
    public int getAllLikes(long blogId) throws BlogNotFoundException {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) throw new BlogNotFoundException("Blog not found");
        return blog.getLikes().size();
    }
}
