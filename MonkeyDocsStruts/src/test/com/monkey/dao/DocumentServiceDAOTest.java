package com.monkey.dao;

import com.monkey.mvc.DocumentController;
import com.monkey.service.DocumentService;
import com.monkey.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
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
    private DocumentController documentController;

    @Before
    public void before() {

    }
    @After
    public void after() {

    }
    @Test
    public void createEmptyDocument() {
        User creator = userDAO.findAll().get(0);
        DocumentController.Input.DocCreation param = new DocumentController.Input.DocCreation();
        param.userId = creator.getId();
        param.mdName = "Test Doc";
        Meta meta = documentController.createDocument(param);
        assert metaDAO.findOne(meta.getId()).getRefUsers().contains(creator);
    }

    @Test
    public void createEmptyRowAndFragment() {

    }
    @Test
    public void createSecondLine() {

    }
    @Test
    public void createFragmentToFirstLine() {

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
