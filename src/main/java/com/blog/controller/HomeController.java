package com.blog.controller;

import com.blog.model.Post;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService ps;
    Long updateID;
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String firstRequest(Model model) {
        List<Post> post=ps.listAll();
        post.forEach(p-> {
            if (p.getContent().length() >= 300) {
                p.setContent(p.getContent().substring(0, 300) + "....");
            }
        });
        if (!post.isEmpty()) {
            model.addAttribute("lists", post);
        }
        System.out.println(post);
        return "index";
    }

    @RequestMapping("/addPost")
    public String addPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getContent());
        model.addAttribute("heading", "CREATE");
        model.addAttribute("formAction", "create");
        return "create";
    }

    @RequestMapping("/create")
    public String updatePost(@ModelAttribute("post") Post post, ModelMap model) {
        model.addAttribute("message", "Creation successfull");
        ps.save(post);
        return "addMessage";
    }

    @RequestMapping("/read/{id}")
    public String openContent(@PathVariable("id") long id, ModelMap model) {
        Post p = ps.get(id);
        model.addAttribute("post", p);
        return "content";
    }

    @RequestMapping("read/edit/{id}")
    public String editContent(@PathVariable("id") long id, ModelMap model) {
        Post post = ps.get(id);
        updateID=id;
        model.addAttribute("post", post);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getContent());
        model.addAttribute("heading", "UPDATE");
        model.addAttribute("formAction", "updatePost");
        return "create";
    }

    @RequestMapping("read/edit/updatePost")
    public String update(@ModelAttribute("post") Post post, ModelMap model) {
        post.setId(updateID);
        post.setContent(post.getContent());
        post.setTitle(post.getTitle());
        ps.save(post);
//        System.out.println("title of the post is:"+post.getTitle());
//        System.out.println("Id is:"+post.getId());
//        System.out.println("content is:"+post.getContent());
       model.addAttribute("message", "Updated Successfully");
        return "updateMessage";
    }


    @RequestMapping("read/delete/delete/{id}")
    public String deleteContent(@PathVariable("id") long id) {
        ps.delete(id);
        return "del";
    }

    @RequestMapping("read/delete/{id}")
    public String confirmDelete(@PathVariable("id") long id,ModelMap model)
    {
        model.addAttribute("id",id);
        return "confirmDelete";
    }
}