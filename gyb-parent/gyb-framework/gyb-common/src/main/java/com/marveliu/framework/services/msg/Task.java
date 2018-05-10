package com.marveliu.framework.services.msg;

public interface Task {


    // 构造特定Task需要发送的消息类型,传入sys_msg msg
    public String MsgBuilder();

}
