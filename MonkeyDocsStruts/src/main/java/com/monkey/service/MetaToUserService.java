package com.monkey.service;

import com.monkey.dao.MetaToUserDAO;
import com.monkey.entity.MetaToUser;
import com.monkey.service.base.CrdService;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("metaToUserService")
public class MetaToUserService extends CrdService<MetaToUser, Integer> {
    @Resource(name = "metaToUserDAO")
    public void setSuperDAO(MetaToUserDAO metaToUserDAO) { super.dao = metaToUserDAO; }
}
