package com.monkey.dao;

import com.monkey.dao.base.CrdDAO;
import com.monkey.entity.MetaToUser;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("metaToUserDAO")
@Transactional
public class MetaToUserDAO extends CrdDAO<MetaToUser> {
    protected MetaToUserDAO() { super(MetaToUser.class); }
}
