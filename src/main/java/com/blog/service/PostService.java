package com.blog.service;
import java.util.List;

import com.blog.model.Post;
import com.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class PostService {
    @Autowired
    public PostRepository repo;

    public void save(Post post) {
        repo.save(post);
    }

    public List<Post> listAll() { return (List<Post>) repo.findAll();}

    public Post get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}