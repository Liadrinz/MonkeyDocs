package com.monkey.action;

import com.monkey.action.base.StrutsRestController;
import com.monkey.entity.Fragment;
import com.monkey.service.FragmentService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.annotation.Resource;

@Results(@Result(name = "success", type = "redirectAction", params = {"namespace", "/fragment", "actionName", "${id}"}))
public class FragmentController extends StrutsRestController<Fragment> {
    public FragmentController() { model = new Fragment(); }
    @Resource(name = "fragmentService")
    public void setSuperService(FragmentService fragmentService) { super.service = fragmentService; }
}
