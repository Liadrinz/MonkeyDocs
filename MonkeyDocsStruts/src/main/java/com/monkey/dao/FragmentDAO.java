package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Fragment;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("fragmentDAO")
@Transactional
public class FragmentDAO extends CrudDAO<Integer, Fragment> {
    public FragmentDAO() { super(Fragment.class); }
}
