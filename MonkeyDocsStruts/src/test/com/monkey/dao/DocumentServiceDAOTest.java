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
import org.w3c.dom.ls.LSException;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private RowDAO rowDAO;
    @Resource
    private FragmentDAO fragmentDAO;
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
        User creator = userDAO.findAll().get(0);
        Meta testDoc = docService.createMeta(creator.getId(), "Test Doc for first row");

        assert testDoc.getRefRows() == null;

        Row firstRow = new Row();
        firstRow.setRefMeta(testDoc);
        rowDAO.create(firstRow);

        MetaToUser metaToUser = new MetaToUser();
        metaToUser.setMdId(testDoc.getId());
        metaToUser.setRole("creator");
        List<MetaToUser> results = metaToUserDAO.findByExample(metaToUser);
        assert results.get(0).getRefUser().getId() == creator.getId();

        Fragment firstFragment = new Fragment();
        firstFragment.setRefRow(firstRow);
        firstFragment.setFType(true);
        firstFragment.setFContent("");
        firstFragment.setRefUser(creator);
        fragmentDAO.create(firstFragment);

        assert firstRow.getRefMeta().getId() == testDoc.getId();
    }
    @Test
    public void createSecondLine()
    {
        User creator = userDAO.findAll().get(0);
        Meta testDoc = docService.createMeta(creator.getId(), "Test Doc for second rows");
        //testDoc = docService.createSecondRow(testDoc);

        Row row = new Row();
        row.setDocId(testDoc.getId());
        List<Row> rows = rowDAO.findByExample(row);

        assert rows.get(0).getRefMeta().getId() == testDoc.getId();
        assert rows.size() == 1;
        assert rows.get(0).getNextRow() == null;
        Row firstRow = rows.get(0);

        Row secondRow = new Row();
        secondRow.setRefMeta(testDoc);
        secondRow.setPreRow(firstRow.getId());
        secondRow = rowDAO.create(secondRow);
        firstRow.setNextRow(secondRow.getId());

        row.setNextRow(secondRow.getId());
        rows = rowDAO.findByExample(row);
        assert rows.size() == 2;
        assert rows.get(0).getPreRow() == null;
        assert rows.get(0).getNextRow() == secondRow.getId();
        assert rows.get(1).getPreRow() == firstRow.getId();
        assert rows.get(1).getNextRow() == null;

    }
    @Test
    public void createFragmentToLine()
    {
        User creator = userDAO.findAll().get(0);
        Meta testDoc = docService.createMeta(creator.getId(), "Test Doc for second rows");

        Row row = new Row();
        row.setDocId(testDoc.getId());
        List<Row> rows = rowDAO.findByExample(row);

        Fragment newFragment = new Fragment();
        newFragment.setRefRow(rows.get(0));
        newFragment.setFType(true);
        newFragment.setFContent("");
        newFragment.setRefUser(creator);
        newFragment = fragmentDAO.create(newFragment);

    }
    @Test
    public void insertToFragment() {

    }
    @Test
    public void deleteFromFragment() {

    }

    @Test
    public void insertBetweenFragment()
    {
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
