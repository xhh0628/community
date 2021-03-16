package com.xhh_study1.community.controller;

import com.xhh_study1.community.dto.CommentCreatDTO;
import com.xhh_study1.community.dto.ResultDTO;
import com.xhh_study1.community.exception.CustomizeErrorCode;
import com.xhh_study1.community.model.Comment;
import com.xhh_study1.community.model.User;
import com.xhh_study1.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreatDTO commentCreatDTO, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        if (commentCreatDTO==null || StringUtils.isBlank(commentCreatDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreatDTO.getParentId());
        comment.setContent(commentCreatDTO.getContent());
        comment.setType(commentCreatDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return ResultDTO.okOf();
    }
}
