package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.MessageDAO;
import com.monkey.entity.Message;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/rest/message", "actionName", "${id}"}))
public class MessageController extends StrutsRestController<Message> {
    public MessageController() {
        name = "message";
        model = new Message();
    }
    @Resource(name = "messageDAO")
    public void setSuperDAO(MessageDAO dao) {
        super.dao = dao;
    }
}
