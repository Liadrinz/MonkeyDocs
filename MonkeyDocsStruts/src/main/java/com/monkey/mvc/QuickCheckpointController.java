package com.monkey.mvc;

import com.google.gson.Gson;
import com.monkey.dao.CheckpointDAO;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.entity.Checkpoint;
import com.monkey.entity.Delta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/checkpoint")
public class QuickCheckpointController {
    @Autowired
    private Gson gson;
    @Resource(name = "checkpointDAO")
    private CheckpointDAO checkpointDAO;
    @Resource(name = "metaDAO")
    private MetaDAO metaDAO;
    @Resource(name = "deltaDAO")
    private DeltaDAO deltaDAO;

    @RequestMapping(value = "/{id}/deltas")
    @ResponseBody
    public List<Delta> getDeltaBefore(@PathVariable Integer id) {
        Checkpoint checkpoint = checkpointDAO.findOne(id);
        return deltaDAO.findBefore(checkpoint.getDocid(), checkpoint.getLastDelta());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Checkpoint createOrUpdate(@RequestBody CreateParam param) {
        Checkpoint ex = new Checkpoint();
        ex.setDocid(param.docId);
        ex.setLastDelta(param.lastDelta);
        ex.setRefMeta(metaDAO.findOne(param.docId));
        ex.setRefDelta(deltaDAO.findOne(param.lastDelta));
        if (checkpointDAO.findByExample(ex).size() == 0) {
            checkpointDAO.create(ex);
        } else {
            Integer id = checkpointDAO.findByExample(ex).get(0).getId();
            ex.setId(id);
            checkpointDAO.updateOne(id, ex);
        }
        return ex;
    }

    private static class CreateParam {
        public Integer docId;
        public Integer lastDelta;
    }
}
