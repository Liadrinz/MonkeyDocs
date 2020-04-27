package com.monkey.service;

import com.monkey.dao.*;
import com.monkey.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;

@Service("documentService")
public class DocumentService {
    @Resource
    private FragmentDAO fragmentDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private RowDAO rowDAO;
    @Resource
    private UserDAO userDAO;

    public void createDocument(User creator, Meta meta) {
        metaDAO.create(meta);
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
        creator.getRefMetaToUsers().add(record);
        metaDAO.updateOne(meta.getId(), meta);
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
    }

    public void addParticipant(User user) {

    }
}
