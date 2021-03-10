package com.xhh_study1.community.service;

import com.xhh_study1.community.enums.CommentTypeEnum;
import com.xhh_study1.community.exception.CustomizeErrorCode;
import com.xhh_study1.community.exception.CustomizeException;
import com.xhh_study1.community.mapper.CommentMapper;
import com.xhh_study1.community.mapper.QuestionMapper;
import com.xhh_study1.community.model.Comment;
import com.xhh_study1.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    public void insert(Comment comment) {

        if(comment.getParentId()==null ||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment=commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question=questionMapper.getById(comment.getParentId());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionMapper.updateCommentCount(question);
        }
    }
}
