package com.monkey.mvc;

import com.monkey.entity.Fragment;
import com.monkey.entity.Meta;
import com.monkey.entity.MetaToUser;
import com.monkey.entity.Row;
import com.monkey.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Resource(name = "documentService")
    private DocumentService docService;

    @ResponseBody
    @RequestMapping(value = "/createDoc", method = RequestMethod.POST)
    public Meta createDocument(@RequestBody Input.DocCreation param) {
        return docService.createMeta(param.userId, param.mdName);
    }

    public static class Input {
        public static class DocCreation {
            public int userId;
            public String mdName;
        }
    }
}
