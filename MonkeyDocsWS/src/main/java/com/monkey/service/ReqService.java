package com.monkey.service;

import com.monkey.dao.FragmentDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.dao.RowDAO;
import com.monkey.data.Packet;
import com.monkey.entity.Fragment;
import com.monkey.entity.Meta;
import com.monkey.entity.Row;
import com.monkey.service.base.IncomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReqService extends IncomingService {
    @Resource
    private DocumentService documentService;
    @Override
    public Packet serve(Packet incoming) {
        String document = documentService.getCompleteDocument(incoming.getDocId());
        return new Packet(incoming.getUserId(), incoming.getDocId(), "RES", "", document);
    }
}
