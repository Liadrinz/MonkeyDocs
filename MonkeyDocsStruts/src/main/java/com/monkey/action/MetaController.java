package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.MetaDAO;
import com.monkey.entity.Meta;
import com.monkey.service.MetaService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/meta", "actionName", "${id}"}))
public class MetaController extends StrutsRestController<Meta> {
    public MetaController() {
        name = "meta";
        model = new Meta();
    }
    @Resource(name = "metaDAO")
    public void setSuperDAO(MetaDAO dao) {
        super.dao = dao;
    }
}
