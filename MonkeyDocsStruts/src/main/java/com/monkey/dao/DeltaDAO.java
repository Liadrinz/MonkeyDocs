package com.monkey.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Delta;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("deltaDAO")
@Transactional
public class DeltaDAO extends CrudDAO<Integer, Delta> {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    public DeltaDAO() {
        super(Delta.class);
    }
    public String persistAll(List<Delta> deltas) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sqlBuilder = new StringBuilder("insert into Delta (content, docid, userid) values");
        for (Delta delta : deltas) {
            String content = gson.toJson(delta.getContent()).replace("'", "''");
            sqlBuilder.append(String.format("('%s', %d, %d),", content, delta.getDocid(), delta.getUserid()));
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(";");
        Query query = session.createSQLQuery(sqlBuilder.toString());
        query.executeUpdate();
        return "success";
    }
    public List<Delta> findBefore(int docId, int lastDelta) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(String.format("select * from Delta where docid=%d and id < %d", docId, lastDelta));
        return query.list();
    }
}
