package com.monkey.ot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Delta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OT {
    @Autowired
    private HistoryDAO historyDAO;
    public void apply(final Delta delta, Delta oldDelta) {
        List<Delta> diff = diffFor(oldDelta);
        if (diff.size() == 0) return;
        final List<Object> deltaMat = toMatrix(delta);
        ot(toMatrix(diff), deltaMat);
        applyMatrix(delta, deltaMat);
    }
    private List<Delta> diffFor(Delta oldDelta) {
        List<Delta> result = new ArrayList<>();
        List<String> history = historyDAO.list(oldDelta.getDocid());
        int lengthHasSeen = 0;
        for (int i = 0; i < history.size(); ++i) {
            Delta historyItem = JSON.parseObject(history.get(i), Delta.class);
            lengthHasSeen += lengthFor(historyItem);
            if (lengthHasSeen > lengthFor(oldDelta)) {
                result.add(historyItem);
            }
        }
        return result;
    }
    public int lengthFor(Delta delta) {
        return ((List<JSONObject>)JSON.parseObject(delta.getContent()).get("ops")).size();
    }
    private void applyMatrix(final Delta delta, List<Object> matrix) {
        int retainBefore = (Integer)matrix.get(0);
        String rtBf = String.format("{\"retain\": %d}", retainBefore);
        int retainAfter = (Integer)matrix.get(2);
        String rtAf = String.format("{\"retain\": %d}", retainAfter);
        String insertOrDelete;
        if (Integer.class.isAssignableFrom(matrix.get(1).getClass())) {
            Map<String, Integer> map = new HashMap<>();
            map.put("delete", (Integer)matrix.get(1));
            insertOrDelete = JSON.toJSONString(map);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("insert", (String)matrix.get(1));
            insertOrDelete = JSON.toJSONString(map);
        }
        String content = String.format("{\"ops\": [%s%s%s]}", rtBf, insertOrDelete, rtAf);
        delta.setContent(content);
    }
    private List<List<Object>> toMatrix(List<Delta> deltas) {
        List<List<Object>> result = new ArrayList<>();
        for (Delta delta : deltas) {
            result.add(toMatrix(delta));
        }
        return result;
    }
    private List<Object> toMatrix(Delta delta) {
        List<Object> result = Arrays.asList(null, null, null);
        List<JSONObject> ops = (List<JSONObject>)JSON.parseObject(delta.getContent()).get("ops");
        boolean hasSeenRetain = false;
        for (JSONObject op : ops) {
            Integer retain = op.getInteger("retain");
            if (retain != null) {
                if (!hasSeenRetain) result.set(0, retain);
                else result.set(2, retain);
                hasSeenRetain = true;
            }
            Integer delete = op.getInteger("delete");
            if (delete != null) result.set(1, delete);
            String insert = op.getString("insert");
            if (insert != null) result.set(1, insert);
        }
        return result;
    }
    private void ot(List<List<Object>> diff, final List<Object> delta) {
        for (List<Object> op : diff) {
            singleOT(op, delta);
        }
    }
    private void singleOT(List<Object> op, final List<Object> delta) {
        if ((Integer)delta.get(0) > (Integer)op.get(0))
            delta.set(0, (Integer)delta.get(0) + offset(op));
        if ((Integer)delta.get(2) > (Integer)op.get(2))
            delta.set(2, (Integer)delta.get(2) + offset(op));
    }
    private int offset(List<Object> op) {
        if (Integer.class.isAssignableFrom(op.get(1).getClass()))
            return -(Integer)op.get(1);
        return ((String)op.get(1)).length();
    }
}
