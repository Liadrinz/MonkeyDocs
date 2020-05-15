package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.entity.MetaToUser;
import com.monkey.service.MetaToUserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.annotation.Resource;

@Action("metaToUser")
@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/metaToUser", "actionName", "${id}"}))
public class MetaToUserController extends StrutsRestController<MetaToUser> {
    public MetaToUserController() {
        name = "metaToUser";
        model = new MetaToUser();
    }
    @Resource(name = "metaToUserService")
    public void setSuperService(MetaToUserService metaToUserService) {
        super.service = metaToUserService;
    }

    @Override
    public HttpHeaders index() {
        list = service.getAll();
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
