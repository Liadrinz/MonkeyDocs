package com.monkey.dao;

import com.monkey.entity.Delta;
import com.monkey.entity.User;
import com.monkey.service.HistoryService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DeltaDAOTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private DeltaDAO deltaDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MetaDAO metaDAO;
    @Test
    public void testDAO() {
        Delta delta = new Delta();
        delta.setRefUser(userDAO.findOne(9));
        delta.setRefMeta(metaDAO.findOne(124));
        delta.setContent("{}");
        delta = deltaDAO.create(delta);
        assert deltaDAO.findOne(delta.getId()) != null;
    }
} 
