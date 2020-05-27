package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.DeltaDAO;
import com.monkey.entity.Delta;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/rest/delta", "actionName", "${id}"}))
public class DeltaController extends StrutsRestController<Delta> {
    public DeltaController() {
        name = "delta";
        model = new Delta();
    }
    @Resource(name = "deltaDAO")
    private void setSuperDAO(DeltaDAO dao) {
        super.dao = dao;
    }

}
