package com.monkey.ot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monkey.entity.Delta;

import java.util.*;

public class Operation {
    private String type;
    private int retainBefore = 0;
    private int retainAfter = 0;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRetainBefore() {
        return retainBefore;
    }

    public void setRetainBefore(int retainBefore) {
        this.retainBefore = retainBefore;
    }

    public int getRetainAfter() {
        return retainAfter;
    }

    public void setRetainAfter(int retainAfter) {
        this.retainAfter = retainAfter;
    }

    public static Operation fromDelta(Delta delta) {
        JSONObject content = JSON.parseObject(delta.getContent());
        JSONArray ops = (JSONArray) content.get("ops");
        Operation operation = new Operation();
        boolean before = true, sawRetain = false;
        for (Object op : ops) {
            JSONObject jsonOp = (JSONObject) op;
            if (jsonOp.get("retain") != null) {
                if (!sawRetain) {
                    operation.setRetainBefore(Integer.parseInt(jsonOp.get("retain").toString()));
                } else {
                    operation.setRetainAfter(Integer.parseInt(jsonOp.get("retain").toString()));
                }
                sawRetain = true;
            } else if (jsonOp.get("insert") != null) {
                if (!sawRetain) before = false;
                operation.setType("insert");
                operation.setValue(jsonOp.getString("insert"));
            } else if (jsonOp.get("delete") != null) {
                if (!sawRetain) before = false;
                operation.setType("delete");
                operation.setValue(jsonOp.getString("delete"));
            }
        }
        return operation;
    }

    private static void merge(Operation former, Operation latter) {
        if (former.type.equals("delete")) {
            if (latter.type.equals("delete")) {

            } else if (latter.type.equals("insert")) {

            }
        } else if (former.type.equals("insert")) {
            if (latter.type.equals("delete")) {

            } else if (latter.type.equals("insert")) {
                latter.retainBefore += former.value.length();
            }
        }
    }

    public static void transform(Collection<Delta> deltas) {
        List<Operation> ops = new ArrayList<>();
        for (Delta delta : deltas) {
            ops.add(fromDelta(delta));
        }
        transform(ops, 0);
        int i = 0;
        for (Delta delta : deltas) {
            delta.setContent(ops.get(i).toString());
            i++;
        }
    }

    private static void transform(List<Operation> ops, int p) {
        if (p >= ops.size() - 1) return;
        merge(ops.get(p), ops.get(p + 1));
        transform(ops, p + 1);
    }

    @Override
    public String toString() {
        String rtBf = retainBefore > 0 ? String.format("{\"retain\": %d}, ", retainBefore) : "";
        String rtAft = retainAfter > 0 ? String.format(", {\"retain\": %d}", retainAfter) : "";
        String opS = "{}";
        if (this.type.equals("insert")) {
            Map<String, String> insertMap = new HashMap<>();
            insertMap.put("insert", this.value);
            opS = JSON.toJSONString(insertMap);
        } else if (this.type.equals("delete")) {
            Map<String, Integer> delMap = new HashMap<>();
            delMap.put("delete", Integer.parseInt(this.value));
            opS = JSON.toJSONString(delMap);
        }
        return String.format("{\"ops\": [%s%s%s]}", rtBf, opS, rtAft);
    }

    public static Delta getTestDelta(String content) {
        Delta delta = new Delta();
        delta.setContent(content);
        return delta;
    }

    public static void main(String[] args) {
        Delta former = getTestDelta("{ops: [{\"retain\": 2}, {\"insert\": \"Hello\"}, {\"retain\": 3}]}");
        Delta latter = getTestDelta("{ops: [{\"retain\": 2}, {\"insert\": \"\\n\"}]}");
        List<Delta> deltas = new ArrayList<>();
        deltas.add(former);
        deltas.add(latter);
        transform(deltas);
        System.out.println(deltas);
    }
}
