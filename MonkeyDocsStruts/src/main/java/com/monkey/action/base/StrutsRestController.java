package com.monkey.action.base;

import com.monkey.entity.base.BaseEntity;
import com.monkey.service.base.RestService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.List;

public abstract class StrutsRestController<T extends BaseEntity<T>> extends ActionSupport implements ModelDriven<Object> {
    protected RestService<T> service;
    private int id;
    protected T model;
    private List<T> list;
    public int getId() {
        return id;
    }
    public void setId(String id) {
        this.id = Integer.valueOf(id);
        if (this.id > 0)
            this.model = service.getById(this.id);
    }
    // GET /user?userName=xxx&tel=xxx&email=xxx&id=xxx
    public HttpHeaders index() {
        list = service.getByModel(model);
        return new DefaultHttpHeaders("index").disableCaching();
    }
    // GET /user/1
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
    // POST /user
    public HttpHeaders create() {
        service.create(model);
        return new DefaultHttpHeaders("success");
    }
    // PUT /user/1
    public HttpHeaders update() {
        service.update(id, model);
        return new DefaultHttpHeaders("success");
    }
    // DELETE /user/1
    public HttpHeaders destroy() {
        service.delete(id);
        return new DefaultHttpHeaders("success");
    }
    @Override
    public Object getModel()
    {
        return (list != null ? list : model);
    }
}
