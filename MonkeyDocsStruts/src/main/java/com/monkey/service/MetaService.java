package com.monkey.service;

import com.monkey.dao.MetaDAO;
import com.monkey.entity.Meta;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("metaService")
public class MetaService extends RestService<Meta> {
    @Resource(name = "metaDAO")
    public void setSuperDAO(MetaDAO metaDAO) { super.dao = metaDAO; }
}
