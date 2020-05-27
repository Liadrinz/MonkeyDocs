package com.monkey.mvc;

import com.monkey.dao.DocvisittokenDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.dao.MetaToUserDAO;
import com.monkey.entity.Docvisittoken;
import com.monkey.entity.MetaToUser;
import com.monkey.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/visitdocs")
public class VisitdocsController {
    @Resource(name = "metaToUserDAO")
    private MetaToUserDAO metaToUserDAO;
    @Resource(name = "docvisittokenDAO")
    private DocvisittokenDAO docvisittokenDAO;
    @Resource(name ="metaDAO")
    private MetaDAO metaDAO;
    @RequestMapping("/readonly/{docid}")
    public ModelAndView readonly(@PathVariable String docid){
        Date date =new Date();
        String tokenstring =date+docid+"readonly";
        String token = Security.encryptPwd(tokenstring);
        Docvisittoken docvisittoken = new Docvisittoken();
        docvisittoken.setCreatetime(new Date());
        docvisittoken.setRefMeta(metaDAO.findOne(Integer.parseInt(docid)));
        docvisittoken.setToken(token);
        docvisittokenDAO.create(docvisittoken);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setExposeModelAttributes(true);
        redirectView.setUrl("/DocEditPage.html?docid="+docid);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        modelAndView.addObject("visittoken",token);
        return modelAndView;
    }
    @RequestMapping("/write/{docid}")
    public RedirectView writedoc(@PathVariable String docid){

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/DocEditPage.html?docid="+docid+"&action=join");
        return redirectView;
    }

}
