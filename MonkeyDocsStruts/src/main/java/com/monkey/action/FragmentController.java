package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.dao.RowDAO;
import com.monkey.dao.UserDAO;
import com.monkey.entity.Fragment;
import com.monkey.service.FragmentService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.HttpHeaders;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/fragment", "actionName", "${id}"}))
public class FragmentController extends StrutsRestController<Fragment> {
    @Resource
    private UserDAO userDAO;
    @Resource
    private RowDAO rowDAO;
    public FragmentController() {
        name = "fragment";
        model = new Fragment();
    }
    @Override
    public HttpHeaders create() {
        model.setRefUser(userDAO.findOne(model.getUserId()));
        model.setRefRow(rowDAO.findOne(model.getRowId()));
        return super.create();
    }
    @Resource(name = "fragmentService")
    public void setSuperService(FragmentService fragmentService) { super.service = fragmentService; }
}
