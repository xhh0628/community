package com.xhh_study1.community.controller;

import com.xhh_study1.community.dto.CommentDTO;
import com.xhh_study1.community.dto.QuestionDTO;
import com.xhh_study1.community.enums.CommentTypeEnum;
import com.xhh_study1.community.service.CommentService;
import com.xhh_study1.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model){

        QuestionDTO questionDTO=questionService.getById(id);
        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        List<QuestionDTO> relatedQuestions=questionService.selecrRelated(questionDTO);
        //累加阅读量
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
