package com.monkey.service;

import com.monkey.dao.RowDAO;
import com.monkey.entity.Row;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("rowService")
public class RowService extends RestService<Row> {
    @Resource(name = "rowDAO")
    public void setSuperDAO(RowDAO rowDAO) { super.dao = rowDAO; }
}
