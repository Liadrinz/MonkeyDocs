package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.DocvisittokenDAO;
import com.monkey.entity.Docvisittoken;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/docvisittoken", "actionName", "${id}"}))
public class DocvisittokenController extends StrutsRestController<Docvisittoken> {
    public DocvisittokenController(){
        name="docvisittoken";
        model = new Docvisittoken();
    }
    @Resource(name = "docvisittokenDAO")
    public void setSuperDAO(DocvisittokenDAO dao) {
        super.dao = dao;
    }
}
