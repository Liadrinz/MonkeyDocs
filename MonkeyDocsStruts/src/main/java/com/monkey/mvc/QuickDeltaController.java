package com.monkey.mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.monkey.dao.DeltaDAO;
import com.monkey.entity.Delta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/delta")
public class QuickDeltaController {
    private static Gson gson = new Gson();
    @Resource(name = "deltaDAO")
    private DeltaDAO deltaDAO;

    @RequestMapping(value = "/createAll", method = RequestMethod.POST)
    @ResponseBody
    public String createAll(@RequestBody CreateAllParam param) {
        List<Delta> deltas = gson.fromJson(param.deltas, new TypeToken<List<Delta>>(){}.getType());
        return deltaDAO.persistAll(deltas);
    }

    private static class CreateAllParam {
        public String deltas;
    }
}