package com.monkey.dao;

import com.monkey.dao.base.ManyToManyDAO;
import com.monkey.entity.MetaToUser;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("metaToUserDAO")
@Transactional
public class MetaToUserDAO extends ManyToManyDAO<MetaToUser, Integer> {
    protected MetaToUserDAO() { super(MetaToUser.class); }
    public List<Integer> findUsersByMeta(int metaId) {
        return findAll("MetaToUser", "userId", "mdId", metaId);
    }
    public List<Integer> findMetasByUser(int userId) {
        return findAll("MetaToUser", "mdId", "userId", userId);
    }
}
