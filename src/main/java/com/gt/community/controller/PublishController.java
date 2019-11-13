package com.gt.community.controller;

import com.gt.community.dto.QuesstionDTO;
import com.gt.community.model.Quesstion;
import com.gt.community.model.User;
import com.gt.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edText(@PathVariable(name = "id") Integer id,
                         Model model) {
        QuesstionDTO quesstionDTO = questionService.getById(id);
        model.addAttribute("title", quesstionDTO.getTitle());
        model.addAttribute("description", quesstionDTO.getDescription());
        model.addAttribute("tag", quesstionDTO.getTag());
        model.addAttribute("id", quesstionDTO.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Integer id,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            request.getSession().setAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            request.getSession().setAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            request.getSession().setAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("error", "用户未登录");
            return "publish";
        }

        Quesstion quesstion = new Quesstion();
        quesstion.setTitle(title);
        quesstion.setDescription(description);
        quesstion.setTag(tag);
        quesstion.setCreator(user.getId());
        quesstion.setId(id);
        questionService.createOrUpdate(quesstion);
        return "redirect:/";
    }
}
