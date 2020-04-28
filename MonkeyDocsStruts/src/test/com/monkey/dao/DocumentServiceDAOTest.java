package com.monkey.dao;

import com.monkey.service.DocumentService;
import com.monkey.entity.*;
import com.monkey.service.MetaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Unit Test at DAO Level
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class DocumentServiceDAOTest {
    // Injected Resources, add if you need

    @Resource
    private MetaDAO metaDAO;
    @Resource
    private MetaToUserDAO metaToUserDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private DocumentService docService;
    @Resource
    private MetaService metaService;

    @Before
    public void before() {

    }
    @After
    public void after() {

    }
    @Test
    public void createEmptyDocument() {
        // Select a lucky creator
        User creator = userDAO.findAll().get(0);

        // Create Meta
        Meta meta = new Meta();
        meta.setMdName("测试文档");
        meta.setCreateTime(new Date());
        meta.setUpdateTime(new Date());

        docService.createDocument(creator, meta);

        // Validation
        checkMetaInUser(creator, meta);
        checkUserInMeta(creator, meta);
        assert userDAO.findOne(creator.getId()).getRefMetas().contains(meta);
        assert metaDAO.findOne(meta.getId()).getRefUsers().contains(creator);
        assert metaToUserDAO.findAll().size() > 0;
    }
    @Test
    public void createSecondLine() {
        // TODO: to create the second line
    }
    @Test
    public void createFragmentToFirstLine() {
        // TODO: write things to the first line
    }
    @Test
    public void insertToFragment() {

    }
    @Test
    public void deleteFromFragment() {

    }
    @Test
    public void insertAfterFragment() {

    }
    @Test
    public void insertBeforeFragment() {

    }
    private void checkMetaInUser(User user, Meta meta) {
        Set<MetaToUser> metaMTU = metaDAO.findOne(meta.getId()).getRefMetaToUsers();
        assert metaMTU != null;
        Set<User> users = new HashSet<>();
        for (MetaToUser item : metaMTU) {
            users.add(item.getRefUser());
        }
        assert users.contains(user);
    }
    private void checkUserInMeta(User user, Meta meta) {
        Set<MetaToUser> userMTU = userDAO.findOne(user.getId()).getRefMetaToUsers();
        assert userMTU != null;
        Set<Meta> metas = new HashSet<>();
        for (MetaToUser item : userMTU) {
            metas.add(item.getRefMeta());
        }
        assert metas.contains(meta);
    }
}
