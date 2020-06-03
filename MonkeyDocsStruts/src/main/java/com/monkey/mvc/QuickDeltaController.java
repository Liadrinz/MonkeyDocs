package com.monkey.mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/delta")
public class QuickDeltaController {
    @Autowired
    private Gson gson;
    @Resource(name = "deltaDAO")
    private DeltaDAO deltaDAO;
    @Resource(name = "metaDAO")
    private MetaDAO metaDAO;

    @RequestMapping(value = "/createAll", method = RequestMethod.POST)
    @ResponseBody
    public String createAll(@RequestBody CreateAllParam param) {
        List<Delta> deltas = gson.fromJson(param.deltas, new TypeToken<List<Delta>>(){}.getType());
        Integer docId = deltas.get(0).getDocid();
        Meta newMeta = metaDAO.findOne(docId);
        newMeta.setUpdateTime(new Date());
        metaDAO.updateOne(docId, newMeta);
        return deltaDAO.persistAll(deltas);
    }

    public static class CreateAllParam {
        public String deltas;
    }
}
