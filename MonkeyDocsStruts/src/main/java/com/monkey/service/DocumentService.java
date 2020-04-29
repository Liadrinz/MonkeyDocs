package com.monkey.service;

import com.monkey.dao.*;
import com.monkey.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.*;

@Service("documentService")
public class DocumentService {
    @Resource
    private DocumentDAO documentDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private RowDAO rowDAO;
    @Resource
    private FragmentDAO fragmentDAO;
    @Resource
    private  MetaToUserDAO metaToUserDAO;

    public Meta createMeta(Integer userId, String mdName) {
        User creator = userDAO.findOne(userId);
        Meta meta = new Meta();
        meta.setMdName(mdName);
        meta = documentDAO.createEmptyDoc(creator, meta);
        return metaDAO.findOne(meta.getId());
    }

    public void addParticipant(User user) {

    }

    public User getCreator(Meta meta)
    {
        MetaToUser metaToUser = new MetaToUser();
        metaToUser.setMdId(meta.getId());
        metaToUser.setRole("creator");
        List<MetaToUser> results = metaToUserDAO.findByExample(metaToUser);
        return results.get(0).getRefUser();
    }


    public Meta createEmptyRoW(Meta meta)
    {
        Row firstRow = new Row();
        firstRow.setRefMeta(meta);
        rowDAO.create(firstRow);

        Fragment firstFragment = new Fragment();
        firstFragment.setRefRow(firstRow);
        firstFragment.setFType(true);
        firstFragment.setFContent("");

        if(getCreator(meta) != null)
            firstFragment.setRefUser(getCreator(meta));
        else {
            List<User> userList = new ArrayList<User>(meta.getRefUsers());
            User user = userList.get(0);
        }

        fragmentDAO.create(firstFragment);
        return metaDAO.findOne(meta.getId());
    }
}
