package com.gt.community.service;

import com.gt.community.dto.PaginationDTO;
import com.gt.community.dto.QuesstionDTO;
import com.gt.community.mapper.QuesstionMapper;
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
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = quesstionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotaPage()) {
            page = paginationDTO.getTotaPage();
        }
        //计算方式 size(page-1)
        Integer offset = size * (page - 1);
        List<Quesstion> quesstions = quesstionMapper.list(offset,size);
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
}
