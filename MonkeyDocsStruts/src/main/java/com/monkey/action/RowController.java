package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.entity.Row;
import com.monkey.service.RowService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/row", "actionName", "${id}"}))
public class RowController extends StrutsRestController<Row> {
    public RowController() {
        name = "row";
        model = new Row();
    }
    @Resource(name = "rowService")
    public void setSuperService(RowService rowService) { super.service = rowService; }
}
