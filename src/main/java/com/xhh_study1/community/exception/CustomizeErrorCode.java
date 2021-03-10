package com.xhh_study1.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"该问题不存在或已删除！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003,"当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004,"服务器异常！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！！！"),
    COMMENT_NOT_FOUND(2006,"该评论不存在或已删除");


    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public Integer getCode() { return code; }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code=code;
    }





}
