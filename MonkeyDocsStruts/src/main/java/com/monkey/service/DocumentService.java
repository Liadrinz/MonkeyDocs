package com.monkey.service;

import com.monkey.dao.*;
import com.monkey.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public Meta createMeta(Integer userId, String mdName) {
        User creator = userDAO.findOne(userId);
        Meta meta = new Meta();
        meta.setMdName(mdName);
        meta = documentDAO.createEmptyDoc(creator, meta);
        return metaDAO.findOne(meta.getId());
    }

    public void addParticipant(User user) {

    }
}
