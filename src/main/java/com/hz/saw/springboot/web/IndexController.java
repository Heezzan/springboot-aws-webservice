package com.hz.saw.springboot.web;

import com.hz.saw.springboot.config.auth.LoginUser;
import com.hz.saw.springboot.config.auth.dto.SessionUser;
import com.hz.saw.springboot.service.posts.PostsService;
import com.hz.saw.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, HttpSession httpSession) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
//            System.out.println(user.getName()+", "+user.getEmail()+", "+user.getPicture());
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
