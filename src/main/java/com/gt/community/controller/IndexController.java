package com.gt.community.controller;

import com.gt.community.dto.QuesstionDTO;
import com.gt.community.mapper.UserMapper;
import com.gt.community.model.User;
import com.gt.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                  User user = userMapper.findByToken(token);
                  if(user!=null){
                      request.getSession().setAttribute("user",user);
                  }
                }
            }}
        List<QuesstionDTO> quesstionDTOList =  questionService.list();
        model.addAttribute("quetions",quesstionDTOList);
        return "index";
    }
}
