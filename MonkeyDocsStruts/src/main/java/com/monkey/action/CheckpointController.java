package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.CheckpointDAO;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.entity.Checkpoint;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/rest/checkpoint", "actionName", "${id}"}))
public class CheckpointController extends StrutsRestController<Checkpoint> {
    @Resource(name = "deltaDAO")
    private DeltaDAO deltaDAO;
    @Resource(name = "metaDAO")
    private MetaDAO metaDAO;

    public CheckpointController() {
        name = "checkpoint";
        model = new Checkpoint();
    }
    @Resource(name = "checkpointDAO")
    private void setSuperDAO(CheckpointDAO dao) {
        super.dao = dao;
    }

    @Override
    public HttpHeaders create() {
        model.setRefMeta(metaDAO.findOne(model.getDocid()));
        model.setRefDelta(deltaDAO.findOne(model.getLastDelta()));
        return super.create();
    }
}
