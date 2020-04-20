package com.monkey.service;

import com.monkey.dao.FragmentDAO;
import com.monkey.entity.Fragment;
import com.monkey.service.base.RestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("fragmentService")
public class FragmentService extends RestService<Fragment> {
    @Resource(name = "fragmentDAO")
    public void setSuperDAO(FragmentDAO fragmentDAO) { super.dao = fragmentDAO; }
}
