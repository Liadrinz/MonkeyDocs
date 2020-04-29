package com.monkey.dao;

import com.monkey.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;

@Repository("documentDAO")
@Transactional
public class DocumentDAO {
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private FragmentDAO fragmentDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private RowDAO rowDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private MetaToUserDAO metaToUserDAO;

    public Meta createEmptyDoc(User creator, Meta meta) {
        String mdName = meta.getMdName();
        meta = new Meta();
        meta.setMdName(mdName);
        meta.setCreateTime(new Date());
        meta.setUpdateTime(new Date());
        meta = metaDAO.create(meta);
        MetaToUser record = new MetaToUser();
        record.setRole("creator");
        record.setUserId(creator.getId());
        record.setMdId(meta.getId());
        metaToUserDAO.create(record);
        Row emptyRow = new Row();
        emptyRow.setRefMeta(meta);
        emptyRow = rowDAO.create(emptyRow);
        Fragment emptyFragment = new Fragment();
        emptyFragment.setRefRow(emptyRow);
        emptyFragment.setFType(true);
        emptyFragment.setFContent("");
        emptyFragment.setRefUser(creator);
        fragmentDAO.create(emptyFragment);
        return meta;
    }
}
