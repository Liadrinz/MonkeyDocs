package com.monkey.service;

import com.monkey.dao.MetaToUserDAO;
import com.monkey.entity.MetaToUser;
import com.monkey.service.base.CrdService;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Deprecated
@Service("metaToUserService")
public class MetaToUserService extends RestService<MetaToUser> {
    @Resource(name = "metaToUserDAO")
    public void setSuperDAO(MetaToUserDAO metaToUserDAO) {
        super.dao = metaToUserDAO;
    }
}
