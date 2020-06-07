package com.monkey.dao;

import com.google.gson.Gson;
import com.monkey.entity.*;
import com.monkey.mvc.DocumentController;
import com.monkey.mvc.QuickCheckpointController;
import com.monkey.mvc.QuickDeltaController;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class DAOTest {
    @Autowired
    private Gson gson;
    @Resource
    private UserDAO userDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private MetaToUserDAO metaToUserDAO;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testUserCRUD() {
        List<User> original = userDAO.findAll();
        User user = new User();
        user.setPassword("123456");
        user.setUserName("test2");
        user.setEmail("aaa@bbb.ccc.ddd");
        user.setTel("13744445555");
        user = userDAO.create(user);
        // To assert the user is created
        assert userDAO.findOne(user.getId()).getUserName().equals("test2");
        assert userDAO.findAll().size() == original.size() + 1;
        user.setUserName("test2-modified");
        userDAO.updateOne(user.getId(), user);
        // To assert the username is updated
        assert userDAO.findOne(user.getId()).getUserName().equals("test2-modified");
        userDAO.deleteOne(user.getId());
        // To assert the user is deleteOned
        assert userDAO.findOne(user.getId()) == null;
    }

    @Test
    public void testMetaCRUD() {
        User user = userDAO.findAll().get(0);
        List<Meta> original = metaDAO.findAll();
        Meta meta = new Meta();
        meta.setMdName("Test doc 1");
        meta = metaDAO.create(meta);
        MetaToUser record = new MetaToUser();
        record.setUserId(user.getId());
        record.setMdId(meta.getId());
        record.setRole("creator");
        record = metaToUserDAO.create(record);
        // To assert the document is created and related to the user
        assert metaDAO.findOne(meta.getId()).getMdName().equals("Test doc 1");
        MetaToUser example = new MetaToUser();
        example.setMdId(meta.getId());
        assert metaToUserDAO.findByExample(example).get(0).getUserId() == user.getId();
        assert metaDAO.findAll().size() == original.size() + 1;
        meta.setMdName("Test doc 1 - modified");
        metaDAO.updateOne(meta.getId(), meta);
        // To assert the doc name is updated
        assert metaDAO.findOne(meta.getId()).getMdName().equals("Test doc 1 - modified");
        record.setRole("reader");
        metaToUserDAO.updateOne(record.getId(), record);
        // To assert the privilege of the user to the doc is changed
        assert metaToUserDAO.findOne(record.getId()).getRole().equals("reader");
        metaDAO.deleteOne(meta.getId());
        assert metaDAO.findOne(meta.getId()) == null;
    }
    @Autowired
    private DeltaDAO deltaDAO;
    @Test
    public void testDeltaDAO() {
        Delta delta = new Delta();
        delta.setRefUser(userDAO.findOne(9));
        delta.setRefMeta(metaDAO.findOne(124));
        delta.setContent("{}");
        delta = deltaDAO.create(delta);
        assert deltaDAO.findOne(delta.getId()) != null;
    }
    @Autowired
    private DocumentController documentController;
    @Test
    public void testDocumentController() {
        DocumentController.CreateParam param = new DocumentController.CreateParam();
        param.mdName = "hhh";
        param.userId = 9;
        Meta meta = documentController.createDoc(param);
        assert metaDAO.findOne(meta.getId()) != null;
        System.out.println(meta);
    }
    @Autowired
    private CheckpointDAO checkpointDAO;
    @Test
    public void testCheckpointDAO() {
        Checkpoint ckpt = new Checkpoint();
        ckpt.setDocid(124);
        ckpt.setLastDelta(4254);
        checkpointDAO.create(ckpt);
    }

    @Autowired
    private QuickDeltaController quickDeltaController;
    @Test
    public void testQuickCheckpointController() {
        QuickDeltaController.CreateAllParam param = new QuickDeltaController.CreateAllParam();
        List<Delta> deltas = new ArrayList<>();
        Delta delta = new Delta();
        delta.setContent("");
        delta.setDocid(124);
        delta.setUserid(9);
        delta.setContent("");
        deltas.add(delta);
        param.deltas = gson.toJson(deltas);
        quickDeltaController.createAll(param);
    }
} 
