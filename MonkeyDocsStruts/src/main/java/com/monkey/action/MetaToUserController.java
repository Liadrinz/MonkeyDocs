package com.monkey.action;

import com.monkey.dao.MetaDAO;
import com.monkey.dao.UserDAO;
import com.monkey.entity.MetaToUser;
import com.monkey.service.MetaToUserService;
import com.monkey.service.base.CrdService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.annotation.Resource;
import java.util.List;

@Action(value = "metaToUser")
public class MetaToUserController extends ActionSupport implements ModelDriven<Object> {
    @Resource(name = "metaToUserService")
    private MetaToUserService service;
    private String name = "metaToUser";
    private MetaToUser model = new MetaToUser();
    private List<MetaToUser> list;
    public String getName() {
        return name;
    }

    public HttpHeaders index() {
        list =  service.getByModel(model);
        return new DefaultHttpHeaders("index").disableCaching();
    }
    public HttpHeaders show() {
        return index();
    }
    public HttpHeaders create() {
        return index();
    }
    public HttpHeaders update() {
        return index();
    }
    public HttpHeaders destroy() {
        service.delete(model);
        return new DefaultHttpHeaders("index").disableCaching();
    }
    @Override
    public Object getModel() {
        return list;
    }
}
