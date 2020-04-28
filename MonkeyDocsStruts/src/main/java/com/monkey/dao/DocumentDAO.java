package com.monkey.dao;

import com.monkey.entity.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;

@Repository("documentDAO")
@Transactional
public class DocumentDAO {
    @Resource
    private FragmentDAO fragmentDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private RowDAO rowDAO;
    @Resource
    private UserDAO userDAO;

    public Meta create(User creator, Meta meta) {
        meta.setCreateTime(new Date());
        meta.setUpdateTime(new Date());
        meta = metaDAO.create(meta);
        // Create Many-to-Many record
        MetaToUser record = new MetaToUser();
        record.setRole("creator");
        record.setRefUser(creator);
        record.setRefMeta(meta);
        if (meta.getRefMetaToUsers() == null)
            meta.setRefMetaToUsers(new HashSet<MetaToUser>());
        meta.getRefMetaToUsers().add(record);
        if (creator.getRefMetaToUsers() == null)
            creator.setRefMetaToUsers(new HashSet<MetaToUser>());
        creator.getRefMetas().add(meta);
        if (meta.getRefUsers() == null)
            meta.setRefUsers(new HashSet<User>());
        meta.getRefUsers().add(creator);
        if (creator.getRefMetas() == null)
            meta.setRefUsers(new HashSet<User>());
        creator.getRefMetaToUsers().add(record);
        meta = metaDAO.updateOne(meta.getId(), meta);
        userDAO.updateOne(creator.getId(), creator);
        // Create empty row
        Row emptyRow = new Row();
        emptyRow.setPreRow(0);
        emptyRow.setNextRow(0);
        emptyRow.setRefMeta(meta);
        rowDAO.create(emptyRow);
        // Create empty fragment
        Fragment emptyFragment = new Fragment();
        emptyFragment.setBegin(0);
        emptyFragment.setEnd(0);
        emptyFragment.setRefRow(emptyRow);
        emptyFragment.setFType(true);
        emptyFragment.setFContent("");
        emptyFragment.setRefUser(creator);
        fragmentDAO.create(emptyFragment);
        return meta;
    }
}
