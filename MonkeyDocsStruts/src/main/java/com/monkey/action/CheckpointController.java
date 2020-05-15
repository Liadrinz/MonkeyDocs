package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.CheckpointDAO;
import com.monkey.entity.Checkpoint;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/checkpoint", "actionName", "${id}"}))
public class CheckpointController extends StrutsRestController<Checkpoint> {
    public CheckpointController() {
        name = "checkpoint";
        model = new Checkpoint();
    }
    @Resource(name = "checkpointDAO")
    private void setSuperDAO(CheckpointDAO dao) {
        super.dao = dao;
    }
}
