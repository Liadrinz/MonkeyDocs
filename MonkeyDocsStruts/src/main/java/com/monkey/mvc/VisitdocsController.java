package com.monkey.mvc;

import com.monkey.dao.DocvisittokenDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.dao.MetaToUserDAO;
import com.monkey.entity.Docvisittoken;
import com.monkey.entity.MetaToUser;
import com.monkey.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/visitdocs")
public class VisitdocsController {
    @Resource(name = "metaToUserDAO")
    private MetaToUserDAO metaToUserDAO;
    @Resource(name = "docvisittokenDAO")
    private DocvisittokenDAO docvisittokenDAO;
    @Resource(name ="metaDAO")
    private MetaDAO metaDAO;
    @ResponseBody
    @RequestMapping("/readonly/{docid}")
    public ModelAndView readonly(@PathVariable String docid){
        String tokenstring =System.currentTimeMillis()+docid;
        String token = Security.encryptPwd(tokenstring);
        Docvisittoken docvisittoken = new Docvisittoken();
        docvisittoken.setCreatetime(new Date());
        docvisittoken.setRefMeta(metaDAO.findOne(Integer.parseInt(docid)));
        docvisittoken.setToken(token);
        docvisittoken.setTokentype("visit");
        docvisittokenDAO.create(docvisittoken);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setExposeModelAttributes(true);
        redirectView.setUrl("/DocEditPage.html?docid="+docid);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        modelAndView.addObject("visittoken",token);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/write/{docid}/{token}")
    public RedirectView writedoc(@PathVariable String docid,@PathVariable String token){
        List<Docvisittoken> list = docvisittokenDAO.findAll();
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/DocManager.html");
        for(Docvisittoken docvisittoken:list){
            if(docvisittoken.getMdId()==Integer.parseInt(docid)&&docvisittoken.getTokentype().equals("write")
                    &&docvisittoken.getToken().equals(token)){
                Instant instant = Instant.now();
                Instant tokencreatetime = Instant.ofEpochMilli(docvisittoken.getCreatetime().getTime());
                long seconds = Duration.between(tokencreatetime,instant).getSeconds();
                if(seconds>600){
                    redirectView.setUrl("/DocManager.html");
                }else {
                    redirectView.setUrl("/DocEditPage.html?docid="+docid+"&action=join"+"&writetoken="+token);
                }
            }
        }


        return redirectView;
    }
    @ResponseBody
    @RequestMapping("/creatWritetoken/{docid}")
    public Tokenpost writedoct(@PathVariable String docid){
        Tokenpost tokenpost = new Tokenpost();
        List<Docvisittoken> list = docvisittokenDAO.findAll();
        for(Docvisittoken docvisittoken:list){
            if(docvisittoken.getMdId()==Integer.parseInt(docid)&&docvisittoken.getTokentype().equals("write")){
                Instant instant = Instant.now();
                Instant tokencreatetime = Instant.ofEpochMilli(docvisittoken.getCreatetime().getTime());
                long seconds = Duration.between(tokencreatetime,instant).getSeconds();
                if(seconds>600){
                    docvisittokenDAO.deleteOne(docvisittoken.getId());
                }else {
                    tokenpost.mdId = Integer.parseInt(docid);
                    tokenpost.token = docvisittoken.getToken();
                    return tokenpost;
                }
            }
        }
        String tokenstring =System.currentTimeMillis()+docid;
        Docvisittoken docvisittoken = new Docvisittoken();
        docvisittoken.setToken(Security.encryptPwd(tokenstring));
        docvisittoken.setCreatetime(new Date());
        docvisittoken.setRefMeta(metaDAO.findOne(Integer.parseInt(docid)));
        docvisittoken.setTokentype("write");
        Docvisittoken  docvisittoken1 = docvisittokenDAO.create(docvisittoken);
        tokenpost.mdId = Integer.parseInt(docid);
        tokenpost.token = docvisittoken1.getToken();
        return tokenpost;
    }

    @ResponseBody
    @RequestMapping(value = "/jugvisittoken",method = RequestMethod.POST)
    public Boolean jugvisittoken(@RequestBody Tokenpost vistittokenpost){
        Boolean ifhashtoken =false;
        List<Docvisittoken> docvisittokens = new ArrayList<Docvisittoken>();
        List<Docvisittoken> list = docvisittokenDAO.findAll();
        for(Docvisittoken docvisittoken:list){
            if(docvisittoken.getMdId() == vistittokenpost.mdId){
                    if(docvisittoken.getToken().equals(vistittokenpost.token)){
                        ifhashtoken =true;
                    }
            }
        }
        return ifhashtoken;
    }
    @ResponseBody
    @RequestMapping(value = "/jugwritetoken",method = RequestMethod.POST)
    public Boolean jugwritetoken(@RequestBody Tokenpost writetokenpost){
        Boolean ifhashtoken =false;
        List<Docvisittoken> list = docvisittokenDAO.findAll();
        for(Docvisittoken docvisittoken:list){
            if(docvisittoken.getMdId()==writetokenpost.mdId&&docvisittoken.getTokentype().equals("write")
                    &&docvisittoken.getToken().equals(writetokenpost.token)){
                Instant instant = Instant.now();
                Instant tokencreatetime = Instant.ofEpochMilli(docvisittoken.getCreatetime().getTime());
                long seconds = Duration.between(tokencreatetime,instant).getSeconds();
                if(seconds>600){
                   ifhashtoken = false;
                }else {
                    ifhashtoken =true;
                }
            }
        }
        return ifhashtoken;
    }

    @ResponseBody
    @RequestMapping(value = "/joindoc",method = RequestMethod.POST)
    public MetaToUser joindoc(@RequestBody JoinParam joinParam){
        MetaToUser metaToUser = new MetaToUser();
        metaToUser.setMdId(joinParam.mdId);
        metaToUser.setUserId(joinParam.userId);
        metaToUser.setRole("collaborator");
        MetaToUser metaToUser1;
        metaToUser1 =  metaToUserDAO.create(metaToUser);
        return metaToUser1;
    }
    public static class JoinParam {
        public Integer mdId;
        public Integer userId;
    }
    public static class Tokenpost {
        public Integer mdId;
        public String token;
    }

}
