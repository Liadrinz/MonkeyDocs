package com.monkey.service;

import com.monkey.dao.CheckpointDAO;
import com.monkey.entity.Checkpoint;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Deprecated
@Service("checkpointService")
public class CheckpointService extends RestService<Checkpoint> {
    @Resource(name = "checkpointDAO")
    private void setSuperDAO(CheckpointDAO dao) {
        super.dao = dao;
    }
}
