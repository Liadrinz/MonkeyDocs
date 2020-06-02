package com.monkey.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAO extends CrudDAO<Integer, Message> {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    protected MessageDAO() {
        super(Message.class);
    }
    public String persistAll(List<Message> messages) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sqlBuilder = new StringBuilder("insert into Message (`text`, sender_id, receiver_id) values");
        for (Message message : messages) {
            String content = gson.toJson(message.getText()).replace("'", "''");
            sqlBuilder.append(String.format("('%s', %d, %d),", content, message.getSenderId(), message.getReceiverId()));
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(";");
        Query query = session.createSQLQuery(sqlBuilder.toString());
        query.executeUpdate();
        return "success";
    }
}
