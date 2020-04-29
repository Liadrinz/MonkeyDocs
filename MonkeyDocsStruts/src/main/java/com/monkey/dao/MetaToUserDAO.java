package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.MetaToUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("metaToUserDAO")
@Transactional
public class MetaToUserDAO extends CrudDAO<Integer, MetaToUser> {
    protected MetaToUserDAO() { super(MetaToUser.class); }

    @Override
    public MetaToUser create(MetaToUser entity) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(String.format("insert into DocumentMeta_has_User (mdId, userId, role) values (%d, %d, '%s')", entity.getMdId(), entity.getUserId(), entity.getRole()));
        query.executeUpdate();
        List<MetaToUser> results = this.findByExample(entity);
        return results.size() > 0 ? results.get(0) : null;
    }
}
