package com.monkey.mvc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.monkey.dao.MessageDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
public class QuickMessageController {
    @Autowired
    private Gson gson;
    @Resource(name =  "messageDAO")
    private MessageDAO messageDAO;

    private static class CreateAllParam {
        public Integer sender;
        public String receivers;
        public String text;
    }

    @RequestMapping("/createAll")
    @ResponseBody
    public void createAll(@RequestBody CreateAllParam param) {
        List<Message> messages = new ArrayList<>();
        List<Integer> receivers = gson.fromJson(param.text, new TypeToken<List<Integer>>(){}.getType());
        for (Integer receiver : receivers) {
            Message message = new Message();
            message.setSenderId(param.sender);
            message.setReceiverId(receiver);
            message.setText(param.text);
            messages.add(message);
        }
        messageDAO.persistAll(messages);
    }
}
