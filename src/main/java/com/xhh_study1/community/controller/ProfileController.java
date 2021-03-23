package com.xhh_study1.community.controller;

import com.xhh_study1.community.dto.PaginationDTO;
import com.xhh_study1.community.model.User;
import com.xhh_study1.community.service.NotificationService;
import com.xhh_study1.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    private String profile(HttpServletRequest request,
                           @PathVariable(name = "action")String action,
                           Model model,
                           @RequestParam(name = "page",defaultValue = "1") Integer page,
                           @RequestParam(name = "size",defaultValue = "5") Integer size){

        User user=(User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)){

            PaginationDTO paginationDTO=questionService.listByUserId(user.getId(),page,size);
            model.addAttribute("section","questions");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("sectionName","我的提问");
            List<String> test=new ArrayList<>();
            test.add("1");test.add("2");test.add("3");test.add("4");
            model.addAttribute("test",test);
        }else if("replies".equals(action)){
            PaginationDTO paginationDTO=notificationService.list(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("sectionName","我的回复");

        }
        return "profile";
    }

}
