package com.monkey.mvc;

import com.monkey.dao.MetaDAO;
import com.monkey.dao.MetaToUserDAO;
import com.monkey.dao.UserDAO;
import com.monkey.entity.Meta;
import com.monkey.entity.MetaToUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private MetaDAO metaDAO;
    @Autowired
    private MetaToUserDAO metaToUserDAO;
    @Autowired
    private UserDAO userDAO;

    @ResponseBody
    @RequestMapping(value = "/createDoc", method = RequestMethod.POST)
    public Meta createDoc(@RequestBody CreateParam param) {
        Meta meta = new Meta();
        meta.setMdName(param.mdName);
        meta.setCreateTime(new Date());
        meta.setUpdateTime(new Date());
        MetaToUser metaToUser = new MetaToUser();
        meta = metaDAO.create(meta);
        metaToUser.setMdId(meta.getId());
        metaToUser.setUserId(param.userId);
        metaToUser.setRole("creator");
        metaToUserDAO.create(metaToUser);
        return meta;
    }
    public static class CreateParam {
        public Integer userId;
        public String mdName;
    }
}
