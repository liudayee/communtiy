package com.gt.community.service;

import com.gt.community.dto.PaginationDTO;
import com.gt.community.dto.QuesstionDTO;
import com.gt.community.mapper.QuestionMapper;
import com.gt.community.mapper.UserMapper;
import com.gt.community.model.Quesstion;
import com.gt.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totaPage;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totaPage = totalCount / size;
        } else {
            totaPage = totalCount / size + 1;
        }
        if (page > totaPage) {
            page = totaPage;
        }
        if (page < 1||totaPage==0) {
            page = 1;
        }
        paginationDTO.setPagination(totaPage, page);
        //计算方式 size(page-1)
        Integer offset = size * (page - 1);
        List<Quesstion> quesstions = questionMapper.list(offset, size);
        List<QuesstionDTO> quesstionDTOList = new ArrayList<>();
        for (Quesstion quesstion : quesstions) {
            User user = userMapper.findById(quesstion.getCreator());
            QuesstionDTO quesstionDTO = new QuesstionDTO();
            //复制对象属性从quesstion-quesstionDTO
            BeanUtils.copyProperties(quesstion, quesstionDTO);
            quesstionDTO.setUser(user);
            quesstionDTOList.add(quesstionDTO);
        }
        paginationDTO.setQuestions(quesstionDTOList);
        return paginationDTO;
    }

    public PaginationDTO listById(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totaPage;
        Integer totalCount = questionMapper.countById(userId);


        if (totalCount % size == 0) {
            totaPage = totalCount / size;
        } else {
            totaPage = totalCount / size + 1;
        }
        if (page > totaPage) {
            page = totaPage;
        }
        if (page < 1||totaPage==0) {
            page = 1;
        }

        paginationDTO.setPagination(totaPage, page);

        //计算方式 size(page-1)
        Integer offset = size * (page - 1);
        List<Quesstion> quesstions = questionMapper.listById(userId, offset, size);
        List<QuesstionDTO> quesstionDTOList = new ArrayList<>();
        for (Quesstion quesstion : quesstions) {
            User user = userMapper.findById(quesstion.getCreator());
            QuesstionDTO quesstionDTO = new QuesstionDTO();
            //复制对象属性从quesstion-quesstionDTO
            BeanUtils.copyProperties(quesstion, quesstionDTO);
            quesstionDTO.setUser(user);
            quesstionDTOList.add(quesstionDTO);
        }
        paginationDTO.setQuestions(quesstionDTOList);
        return paginationDTO;
    }

    public QuesstionDTO getById(Integer id) {
        Quesstion quesstion = questionMapper.getById(id);
        QuesstionDTO quesstionDTO = new QuesstionDTO();
        BeanUtils.copyProperties(quesstion, quesstionDTO);
        quesstionDTO.setUser(userMapper.findById(quesstion.getCreator()));
        return quesstionDTO;
    }
}
