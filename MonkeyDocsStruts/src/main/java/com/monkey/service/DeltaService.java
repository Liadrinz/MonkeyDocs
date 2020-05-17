package com.monkey.service;

import com.monkey.dao.DeltaDAO;
import com.monkey.entity.Delta;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Deprecated
@Service("deltaService")
public class DeltaService extends RestService<Delta> {
    @Resource(name = "deltaDAO")
    private void setSuperDAO(DeltaDAO dao) {
        super.dao = dao;
    }
}
