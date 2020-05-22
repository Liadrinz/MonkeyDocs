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
    public RedirectView readonly(@PathVariable String docid){
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
        redirectView.setExposeModelAttributes(false);
        redirectView.setUrl("http://localhost:8083/md/test.html?"+"visittoken="+token);
        return redirectView;
    }
    @RequestMapping("/write/{docid}/{iflogin}")
    public RedirectView writedoc(@PathVariable String docid, @PathVariable String iflogin){

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        if(iflogin.equals("islogin")){
            redirectView.setUrl("http://localhost:8083/md/test2.html");
            MetaToUser metaToUser = new MetaToUser();
            metaToUser.setMdId(Integer.parseInt(docid));
            metaToUser.setUserId(9);
            metaToUser.setRole("collaborator");
            metaToUserDAO.create(metaToUser);
        }else if(iflogin.equals("unlogin")){
            redirectView.setUrl("http://localhost:8083/md/login.html");
        }
        return redirectView;
    }

}
