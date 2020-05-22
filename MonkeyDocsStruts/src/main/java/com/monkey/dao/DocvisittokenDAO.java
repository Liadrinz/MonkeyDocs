package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Docvisittoken;
import com.monkey.entity.MetaToUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository("docvisittokenDAO")
@Transactional
public class DocvisittokenDAO extends CrudDAO<Integer, Docvisittoken> {
    public DocvisittokenDAO(){super(Docvisittoken.class);}

//    @Override
//    public Docvisittoken create(Docvisittoken entity) {
//        Session session = sessionFactory.getCurrentSession();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String creattime = formatter.format(entity.getCreatetime());
//        String s =String.format("insert into `MonkeyDocDB`.`Docvisittoken` (mdId, createtime, token) values (%d, '%s', '%s')",
//                entity.getMdId(),
//                creattime,
//                entity.getToken());
//        Query query = session.createSQLQuery(s);
//        query.executeUpdate();
//        List<Docvisittoken> results = this.findByExample(entity);
//        return results.size() > 0 ? results.get(0) : null;
//    }
}
