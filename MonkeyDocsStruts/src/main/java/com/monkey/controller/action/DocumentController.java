package com.monkey.controller.action;

import com.monkey.dao.UserDAO;
import com.monkey.entity.Meta;
import com.monkey.service.DocumentService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.annotation.Resource;

@Action(value = "document")
public class DocumentController extends ActionSupport {
    @Resource
    private UserDAO userDAO;
    @Resource
    DocumentService docService;
    private Integer userId;
    private String mdName;

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getMdName() { return mdName; }
    public void setMdName(String mdName) { this.mdName = mdName; }

    public HttpHeaders create() {
        Meta meta = new Meta();
        meta.setMdName(mdName);
        docService.createDocument(userDAO.findOne(userId), meta);
        return new DefaultHttpHeaders("show");
    }
}
