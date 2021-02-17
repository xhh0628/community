package com.xhh_study1.community.service;

import com.xhh_study1.community.dto.QuestionDTO;
import com.xhh_study1.community.mapper.QuestionMapper;
import com.xhh_study1.community.mapper.UserMapper;
import com.xhh_study1.community.model.Question;
import com.xhh_study1.community.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;


    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for (Question question : questions) {
           User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
