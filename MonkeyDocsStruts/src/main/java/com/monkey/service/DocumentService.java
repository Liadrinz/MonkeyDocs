package com.monkey.service;

import com.monkey.dao.*;
import com.monkey.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;

@Service("documentService")
public class DocumentService {
    @Resource
    private DocumentDAO documentDAO;
    public Meta createDocument(User creator, Meta meta) {
        return documentDAO.create(creator, meta);
    }

    public void addParticipant(User user) {

    }
}
