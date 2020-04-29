package com.monkey.dao;

import com.monkey.entity.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class MetaToUserDAOTest {
    @Resource
    private MetaToUserDAO metaToUserDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private RowDAO rowDAO;
    @Resource
    private FragmentDAO fragmentDAO;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void insertIntoMetaToUser() {
        MetaToUser record = new MetaToUser();
        record.setRole("collaborator");
        record.setMdId(18);
        record.setUserId(89);
        metaToUserDAO.create(record);
    }

    @Test
    public void createEmptyRow() {
        Meta meta = new Meta();
        meta.setMdName("Test Doc From JUnit");
        meta.setCreateTime(new Date());
        meta.setUpdateTime(new Date());
        meta = metaDAO.create(meta);
        MetaToUser record = new MetaToUser();
        record.setRole("creator");
        record.setUserId(9);
        record.setMdId(meta.getId());
        Row row = new Row();
        row.setRefMeta(meta);
        rowDAO.create(row);
    }

    @Test
    public void c() {
        User creator = userDAO.findOne(9);
        Meta meta = new Meta();
        meta.setMdName("Test Doc from JUnit");
        meta.setCreateTime(new Date());
        meta.setUpdateTime(new Date());
        meta = metaDAO.create(meta);
        MetaToUser record = new MetaToUser();
        record.setRole("creator");
        record.setUserId(creator.getId());
        record.setMdId(meta.getId());
        metaToUserDAO.create(record);
        // Create empty row
        Row emptyRow = new Row();
        emptyRow.setDocId(meta.getId());
        rowDAO.create(emptyRow);
        // Create empty fragment
        Fragment emptyFragment = new Fragment();
        emptyFragment.setRefRow(emptyRow);
        emptyFragment.setFType(true);
        emptyFragment.setFContent("");
        emptyFragment.setRefUser(creator);
        fragmentDAO.create(emptyFragment);
    }
} 
