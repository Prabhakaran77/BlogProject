package com.blog.controller;

import com.blog.dao.Posts;
import com.blog.dao.operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    operation oper;

    @RequestMapping({"/", "/index", "read/*"," read/edit/*"})
    public String firstRequest(Model model) {
        List<Posts> list = oper.fetchAllPosts();
        if (!list.isEmpty()) {
            model.addAttribute("lists", list);
        }
        System.out.println(list);
        return "index";
    }

    @RequestMapping("/addPost")
    public String addPost(Model model) {
        Posts post = new Posts();
        model.addAttribute("post", post);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getContent());
        model.addAttribute("heading", "CREATE");
        model.addAttribute("formAction", "create");
        return "create";
    }

    @RequestMapping("/create")
    public String updatePost(@ModelAttribute("post") Posts post, ModelMap model) {
        model.addAttribute("message", "Creation successfull");
        boolean updateDone = operation.insert(post);
        if (!updateDone) {
            model.addAttribute("message", " Creation unsuccessfull");
        }
        return "addMessage";
    }

    @RequestMapping("/read/{id}")
    public String openContent(@PathVariable("id") long id, ModelMap model) {
        Posts p = oper.getPostById(id);
        model.addAttribute("post", p);
        return "content";
    }

    @RequestMapping("read/edit/{id}")
    public String editContent(@PathVariable("id") long id, ModelMap model) {
        Posts post = oper.getPostById(id);
        operation.updateId=id;
        model.addAttribute("post", post);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getContent());
        model.addAttribute("heading", "UPDATE");
        model.addAttribute("formAction", "updatePost");
        return "create";
    }

    @RequestMapping("read/edit/updatePost")
    public String update(@ModelAttribute("post") Posts post, ModelMap model) {
        oper.update(post);
        System.out.println("title of the post is:"+post.getTitle());
        System.out.println("Id is:"+post.getId());
        System.out.println("content is:"+post.getContent());
        model.addAttribute("message", "Updated Successfully");
        return "updateMessage";
    }


    @RequestMapping("read/delete/{id}")
    public String deleteContent(@PathVariable("id") long id) {
        oper.deletePostById(id);
        return "del";
    }
}
