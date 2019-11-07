package com.gt.community.controller;

import com.gt.community.dto.PaginationDTO;
import com.gt.community.mapper.UserMapper;
import com.gt.community.model.User;
import com.gt.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name ="page" ,defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue ="5")Integer size) {

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
        PaginationDTO paginationDTO =  questionService.list(page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}
