package com.monkey.ot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Delta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OT {
    @Autowired
    private HistoryDAO historyDAO;

    public void ot(final Delta delta, Delta oldDelta) {
        Delta history = DeltaUtil.merge(historyDAO.range(delta.getDocid()));
        if (oldDelta.getContent().equals(history.getContent()))
            return;
        Delta diff = DeltaUtil.sub(history, oldDelta);
        int offset = offsetFor(diff);
        Operation op = Operation.fromDelta(delta);
        
    }

    public int offsetFor(Delta diff) {
        int result = 0;
        List<JSONObject> diffOps = (List<JSONObject>)JSON.parseObject(diff.getContent()).get("ops");
        for (JSONObject op : diffOps) {
            Integer valInt;
            String valStr;
            if ((valInt = op.getInteger("retain")) != null) {
                result += valInt;
            } else if ((valStr = op.getString("insert")) != null) {
                result += valStr.length();
            } else if ((valInt = op.getInteger("delete")) != null) {
                result -= valInt;
            }
        }
        return result;
    }
}
