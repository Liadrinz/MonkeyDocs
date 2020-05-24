package com.monkey.ot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.monkey.entity.Delta;

import java.util.ArrayList;
import java.util.List;

public class DeltaUtil {
    public static Delta sub(Delta src, Delta del) {
        String srcContent = src.getContent();
        String delContent = del.getContent();
        Delta result = new Delta();
        result.setContent(srcContent.substring(delContent.length()));
        return result;
    }
    public static Delta merge(String queryResult) {
        return merge(JSONArray.parseArray(queryResult, Delta.class));
    }
    private static Delta merge(List<Delta> deltas) {
        if (deltas.size() <= 2) {
            return merge(deltas.get(0), deltas.get(1));
        }
        List<Delta> sub = new ArrayList<>(deltas);
        sub.remove(0);
        return merge(deltas.get(0), merge(sub));
    }
    private static Delta merge(Delta a, Delta b) {
        List opsA = (List)JSON.parseObject(a.getContent()).get("ops");
        List opsB = (List)JSON.parseObject(b.getContent()).get("ops");
        opsA.addAll(opsB);
        Delta result = new Delta();
        result.setContent("{ops:" + JSON.toJSONString(opsA) + "}");
        return result;
    }
    public static Delta getTestDelta(String content) {
        Delta delta = new Delta();
        delta.setContent(content);
        return delta;
    }

    public static void main(String[] args) {
        Delta former = getTestDelta("{\"ops\": [{\"insert\": \"H\"}]}");
        Delta latter = getTestDelta("{\"ops\": [{\"retain\": 1}, {\"insert\": \"e\"}]}");
        List<Delta> deltas = new ArrayList<>();
        deltas.add(former);
        deltas.add(latter);
        deltas.add(former);
        Delta delta = merge(deltas);
        System.out.println(delta);
    }
}
