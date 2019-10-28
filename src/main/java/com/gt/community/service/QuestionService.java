package com.gt.community.service;

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

    public List<QuesstionDTO> list() {
        List<Quesstion> quesstions = quesstionMapper.list();
        List<QuesstionDTO> quesstionDTOList = new ArrayList<>();
        for (Quesstion quesstion : quesstions) {
           User user = userMapper.findById(quesstion.getCreator());
           QuesstionDTO quesstionDTO = new QuesstionDTO();
           //复制对象属性从quesstion-quesstionDTO
            BeanUtils.copyProperties(quesstion,quesstionDTO);
            quesstionDTO.setUser(user);
            quesstionDTOList.add(quesstionDTO);
        }
        return quesstionDTOList;
    }
}
