package com.monkey.service;

import com.monkey.dao.*;
import com.monkey.data.UpdateInput;
import com.monkey.entity.Fragment;
import com.monkey.entity.Meta;
import com.monkey.entity.MetaToUser;
import com.monkey.entity.Row;
import com.monkey.util.FragmentUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    @Resource
    private UserDAO userDAO;
    @Resource
    private MetaDAO metaDAO;
    @Resource
    private RowDAO rowDAO;
    @Resource
    private FragmentDAO fragmentDAO;
    @Resource
    private MetaToUserDAO metaToUserDAO;
    public String getCompleteDocument(int docId) {
        List<Fragment> fragments = getFragments(docId);
        return FragmentUtil.concat(fragments);
    }
    public void updateDocument(UpdateInput data) {
        // The cursor is in some fragment
        if (data.getFragId() != null) {
            Fragment content = new Fragment();
            content.setUserId(data.getUserId());
            content.setRefUser(userDAO.findOne(data.getUserId()));
            content.setFContent(data.getContent());
            content.setFType(data.getType());
            content.setRowId(data.getRowId());
            content.setRefRow(rowDAO.findOne(data.getRowId()));
            fragmentDAO.insertBetween(data.getFragId(), content, data.getOffset());
        }
    }
    private List<Fragment> getFragments(int docId) {
        List<Fragment> result = new ArrayList<>();
        Meta meta = metaDAO.findOne(docId);
        Row rowEx = new Row();
        rowEx.setPreRow(0);
        rowEx.setRefMeta(meta);
        Row row = rowDAO.findByExample(rowEx).get(0);
        while (row.getNextRow() != 0) {
            int nextRowId = row.getNextRow();
            Fragment fragEx = new Fragment();
            fragEx.setBegin(0);
            fragEx.setRefRow(row);
            Fragment fragment = fragmentDAO.findByExample(fragEx).get(0);
            while (fragment.getEnd() != 0) {
                int nextFragId = fragment.getEnd();
                result.add(fragment);
                fragment = fragmentDAO.findOne(nextFragId);
            }
            row = rowDAO.findOne(nextRowId);
        }
        return result;
    }
}
