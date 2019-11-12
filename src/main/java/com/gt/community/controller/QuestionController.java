package com.gt.community.controller;

import com.gt.community.dto.QuesstionDTO;
import com.gt.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        QuesstionDTO quesstionDTO= questionService.getById(id);
        model.addAttribute("question",quesstionDTO);
        return "question";
    }
}
