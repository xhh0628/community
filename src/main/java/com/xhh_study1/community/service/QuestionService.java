package com.xhh_study1.community.service;

import com.xhh_study1.community.dto.PaginationDTO;
import com.xhh_study1.community.dto.QuestionDTO;
import com.xhh_study1.community.exception.CustomizeErrorCode;
import com.xhh_study1.community.exception.CustomizeException;
import com.xhh_study1.community.mapper.QuestionMapper;
import com.xhh_study1.community.mapper.UserMapper;
import com.xhh_study1.community.model.Question;
import com.xhh_study1.community.model.User;
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


    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        if(totalCount % size == 0){
            totalPage=totalCount / size;
        }else{
            totalPage=totalCount / size +1;
        }


        if (page>totalPage){
            page=totalPage;
        }
        if (page<1){
            page=1;
        }
        paginationDTO.setPagination(totalPage,page);
        //size*(page-1)
        Integer offSet=size*(page-1);
        List<Question> questions = questionMapper.list(offSet,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
           User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO listByUserId(Long userId, Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        if(totalCount % size == 0){
            totalPage=totalCount / size;
        }else{
            totalPage=totalCount / size +1;
        }

        if (page>totalPage){
            page=totalPage;
        }
        if (page<1){
            page=1;
        }
        paginationDTO.setPagination(totalPage,page);
        //size*(page-1)
        Integer offSet=size*(page-1);
        List<Question> questions = questionMapper.listByUserId(userId,offSet,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question=questionMapper.getById(id);
        if (question==null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user= userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void creatOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            questionMapper.create(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            int update=questionMapper.update(question);
            if (update!=1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question record=new Question();
        record.setId(id);
        record.setViewCount(1);
        questionMapper.updateQuestionCount(record);
        }

}
