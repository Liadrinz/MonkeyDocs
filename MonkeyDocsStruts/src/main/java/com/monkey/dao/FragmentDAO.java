package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Fragment;
import com.monkey.util.FragmentUtil;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Repository("fragmentDAO")
@Transactional
public class FragmentDAO extends CrudDAO<Integer, Fragment> {
    public FragmentDAO() { super(Fragment.class); }
    private Fragment transform(Fragment entity) {
        if (!entity.getFType()) {
            entity.setFContent(String.valueOf(entity.getFContent().length()));
        }
        return entity;
    }
    @Override
    public Fragment create(Fragment entity) {
        entity = transform(entity);
        return super.create(entity);
    }
    @Override
    public Fragment updateOne(Integer id, Fragment model) {
        model = transform(model);
        return super.updateOne(id, model);
    }
    private void mergeTwoFragments(Fragment[] fragments, Integer prevId, Integer nextId, Integer offset) {
        fragments[offset].setBegin(prevId);
        fragments[offset] = create(fragments[offset]);
        fragments[offset + 1].setBegin(fragments[offset].getId());
        fragments[offset + 1].setEnd(nextId);
        fragments[offset + 1] = create(fragments[offset + 1]);
        fragments[offset].setEnd(fragments[offset + 1].getId());
        fragments[offset] = updateOne(fragments[offset].getId(), fragments[offset]);
    }
    public List<Fragment> insertBetween(Integer id, Fragment content, Integer offset) {
        Fragment origin = findOne(id);
        Fragment[] fragments = FragmentUtil.insert(findOne(id), content, offset);
        Integer prevId = origin.getBegin();
        Integer nextId = origin.getEnd();
        if (fragments[0].getFContent().length() == 0) {
            mergeTwoFragments(fragments, prevId, nextId, 1);
        } else if (fragments[2].getFContent().length() == 0) {
            mergeTwoFragments(fragments, prevId, nextId, 0);
        } else {
            fragments[0].setBegin(prevId);
            fragments[0] = create(fragments[0]);
            for (int i = 0; i < 2; ++i) {
                fragments[i + 1].setBegin(fragments[i].getId());
                fragments[i + 1] = create(fragments[i + 1]);
                fragments[i].setEnd(fragments[i + 1].getId());
                fragments[i] = updateOne(fragments[i].getId(), fragments[i]);
            }
        }
        deleteOne(id);
        return Arrays.asList(fragments);
    }
}
