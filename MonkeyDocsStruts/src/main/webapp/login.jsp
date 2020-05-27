<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W4C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN" class=""><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,user-scalable=no"><meta name="google" content="notranslate"><meta name="renderer" content="webkit"><meta name="Pragma" content="no-cache"><meta name="apple-mobile-web-app-capable" content="yes"><meta name="format-detection" content="telephone=no"><meta http-equiv="Cache-Control" content="no-transform"><meta http-equiv="Cache-Control" content="no-siteapp"><meta name="applicable-device" content="pc,mobile"><title>Monkey Doc</title><meta name="keywords" content=""><style type="text/css" lizard-data-styled-components="gXtlRg kmwMDA bcuuIb dfsiVH sfCUt" data-styled-components-is-local="true">

  .gXtlRg{float:left;}

  .kmwMDA > path{fill:#41464b;}

  .lower{box-sizing:border-box;display:inline-block;border-radius:2px;border-width:1px;border-style:solid;background-color:#fff;text-align:center;-webkit-text-decoration:none;text-decoration:none;cursor:pointer;-webkit-transition:opacity 0.3s,border-color 0.3s;transition:opacity 0.3s,border-color 0.3s;color:#fff;border-color:#41464b;background-image:linear-gradient( #41464b,#2c3033 );font-size:14px;font-weight:bold;line-height:1.4;min-width:100px;height:34px;line-height:32px;padding:0 15px;}.lower:focus{outline:none;}.lower:active{opacity:0.9;}.lower:hover{background-image:none;}.lower:hover{background-color:#363b3e;}.lower[disabled]{background-color:#d9dadb;border-color:#c6c7c9;}.lower[disabled]{cursor:not-allowed;background-image:none;}

  .sfCUt{box-sizing:border-box;display:inline-block;border-radius:2px;border-width:1px;border-style:solid;background-color:#fff;text-align:center;-webkit-text-decoration:none;text-decoration:none;cursor:pointer;-webkit-transition:opacity 0.3s,border-color 0.3s;transition:opacity 0.3s,border-color 0.3s;color:#666;border-width:0;background-image:linear-gradient(#fdfdfd,#f8f8f8);box-shadow:0 1px 1px 0 rgba(0,0,0,0.11);position:relative;font-size:12px;font-weight:bold;line-height:1.4;min-width:90px;height:30px;line-height:28px;padding:0 15px;}.sfCUt:focus{outline:none;}.sfCUt:active{opacity:0.9;}.sfCUt:hover{background-image:none;}.sfCUt::before{content:'';display:block;position:absolute;top:-1px;left:-1px;z-index:-1;width:100%;height:100%;border:1px solid rgba(65,70,75,0.05);border-radius:2px;-webkit-transition:opacity 0.3s ease-out;transition:opacity 0.3s ease-out;opacity:0;}.sfCUt:hover{border-radius:1px;}.sfCUt:hover::before{opacity:1;}.sfCUt[disabled]{border-radius:1px;}.sfCUt[disabled]::before{opacity:1;}.sfCUt:hover{background-image:linear-gradient(#fdfdfd,#f8f8f8);}.sfCUt:active{box-shadow:0 1px 1px 0 rgba(0,0,0,0.1);background-image:linear-gradient(#f0f0f0,#f7f7f7);}.sfCUt[disabled]{color:#ccc;background-color:#f7f7f7;}.sfCUt[disabled]::before{border-color:rgba(0,0,0,0.05);}.sfCUt[disabled]{cursor:not-allowed;background-image:none;}

  .dfsiVH{display:inline-block;}.dfsiVH .sm-button{border-color:transparent;}.dfsiVH .sm-button:not(:first-child){margin-left:-1px;border-top-left-radius:0;border-bottom-left-radius:0;}.dfsiVH .sm-button:not(:last-child){border-bottom-right-radius:0;border-top-right-radius:0;}.dfsiVH:hover .sm-button{border-color:#d6d6d6;}
</style><style type="text/css" lizard-data-styled-components="" data-styled-components-is-local="false">
</style><style type="text/css" lizard-data-styled-components="" data-styled-components-is-local="true"></style><style type="text/css" lizard-data-styled-components="" data-styled-components-is-local="false">
</style><style type="text/css" lizard-data-styled-components="hJGdsb" data-styled-components-is-local="true">

  .upper{border-radius:2px;margin-bottom:16px;display:inline-block;width:100%;margin-right:0;}.upper .label{display:block;margin-bottom:8px;font-size:14px;line-height:1;color:#41464b;}.upper .input{position:relative;background:#fff;height:40px;box-shadow:inset 0 1px 3px 0 #e5e5e5;box-sizing:border-box;border-radius:2px;border:1px solid #e5e5e5;-webkit-transition:border-color 0.3s;transition:border-color 0.3s;}.upper .input:hover{border-color:#ccc;}.upper .input input{border:none;width:100%;padding:11px 18px;border-radius:2px;box-sizing:border-box;color:#41464b;font-size:14px;background:transparent;vertical-align:top;height:38px;line-height:38px;}.upper .input input::-webkit-input-placeholder{color:rgba(65,70,75,0.3);font-size:13px;text-align:right;}.upper .input input::-moz-placeholder{color:rgba(65,70,75,0.3);font-size:13px;text-align:right;}.upper .input input:-ms-input-placeholder{color:rgba(65,70,75,0.3);font-size:13px;text-align:right;}.upper .input input::placeholder{color:rgba(65,70,75,0.3);font-size:13px;text-align:right;}.upper .input input:-webkit-autofill{box-shadow:inset 0 1px 3px 0 #e5e5e5,0 0 0 30px #fff inset;}.upper .input input:-webkit-autofill:first-line{font-size:14px;}.upper .input .forgetPass{position:absolute;font-size:12px;top:-20px;right:0;line-height:1;}

  .upper .areaCode span{margin-left:18px;line-height:24px;}
</style><style type="text/css" lizard-data-styled-components="" data-styled-components-is-local="false">
</style><style type="text/css" lizard-data-styled-components="izpGDa lgVKQA dEKxIf ddhstn bpBmPN iCANTr czzneN gBlFBX bZrWhJ lfWcNR fObdpT dlXXuA fKoDQx" data-styled-components-is-local="true">

  .czzneN .sm-modal-footer button + button{margin-left:10px;margin-bottom:0;}.czzneN .sm-modal-footer a + button{margin-left:10px;margin-bottom:0;}

  .thirdtop{margin:0 auto;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;}.thirdtop .main > .title{font-family:serif;font-size:24px;text-align:center;color:#41464b;-webkit-letter-spacing:2px;-moz-letter-spacing:2px;-ms-letter-spacing:2px;letter-spacing:2px;margin:0 auto 20px;line-height:30px;width:264px;position:relative;font-weight:bold;}.thirdtop .main > .title span{position:absolute;bottom:7px;}

  @media (max-width:700px){.thirdtop .main > .title{font-size:20px;}}

  .thirdtop .main .switch-title div{padding-bottom:18px;color:rgba(65,70,75,0.5);}

  .thirdtop .main > div > .form-wrapper{width:520px;height:auto;box-sizing:border-box;padding:60px 90px;background-color:#fff;}@media (max-width:700px){.thirdtop .main > div > .form-wrapper{width:346px;padding:32px 23px;height:auto;}}  .thirdtop .main > div > .form-wrapper > div{position:relative;}  .thirdtop .main .submit{width:100%;height:40px;margin-top:6px;font-weight:500;font-size:14px;}  .thirdtop .main .switch-panel{box-shadow:0 2px 3px 0 rgba(213,213,213,0.7);}

  .swith{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;}

  .bZrWhJ .submit{margin-top:40px !important;}

  .bottom{position:relative;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;margin-top:40px;}.bottom div{width:20px;height:18px;padding:0 30px;background:url('https://assets.smcdn.cn/static/lizard-service-login/assets/thirdPartLoginIcon.3ebb3f27.png') no-repeat;background-size:18px;background-origin:content-box;overflow:hidden;display:inline-block;margin-top:0;cursor:pointer;}.bottom div > span{position:relative;left:29px;font-size:14px;top:-4px;}

  .maillogin{line-height:24px;position:relative;top:-97px;}.maillogin span{font-size:14px;color:#a5a5a5;}.maillogin .changeVerify{float:right;text-align:right;margin-top:4px;}.maillogin .changeVerify .link{cursor:pointer;color:#666666;}

  .lgVKQA{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-flex-direction:column;-ms-flex-direction:column;flex-direction:column;-webkit-box-flex:1;-webkit-flex-grow:1;-ms-flex-positive:1;flex-grow:1;-webkit-align-items:center;-webkit-box-align:center;-ms-flex-align:center;align-items:center;box-sizing:border-box;min-width:618px;margin:0 auto;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;height:100%;width:100%;position:absolute;line-height:initial !important;}@media (max-width:700px){.lgVKQA{min-width:inherit;}}

  .dEKxIf{position:fixed;top:0;left:0;width:100%;z-index:1;height:50px;}.dEKxIf a{display:inline-block;padding:0 30px;}

  .dlXXuA{font-size:14px;color:#494949;margin-top:20px;text-align:center;cursor:pointer;}.dlXXuA .switch-tips{font-weight:400;margin-right:10px;}.dlXXuA .to-login{position:fixed;z-index:2;}

  .top{position:relative;top:80px;margin-bottom:50px;-webkit-transform-origin:top center;-ms-transform-origin:top center;transform-origin:top center;width:100%;height:calc(100% - 130px);}@media (max-width:700px){.top{width:auto;}}

  @-webkit-keyframes izpGDa{from{-webkit-transform:translateX(-20px);-ms-transform:translateX(-20px);transform:translateX(-20px);opacity:0;}to{-webkit-transform:translateX(0);-ms-transform:translateX(0);transform:translateX(0);opacity:1;}}@keyframes izpGDa{from{-webkit-transform:translateX(-20px);-ms-transform:translateX(-20px);transform:translateX(-20px);opacity:0;}to{-webkit-transform:translateX(0);-ms-transform:translateX(0);transform:translateX(0);opacity:1;}}

  .secondtop{-webkit-animation:izpGDa 0.5s linear;animation:izpGDa 0.5s linear;}@media (max-width:700px){.secondtop{-webkit-animation:none;animation:none;}}

  .fKoDQx{position:absolute;top:9px;right:20px;opacity:1;}.fKoDQx .sm-button{font-size:14px;color:#666;font-weight:400;min-width:52px;width:52px;padding:0;}

  .tiShi{font-size: 12px;color:red;}
</style><style type="text/css" lizard-data-styled-components="" data-styled-components-is-local="false">
  input{outline:none;}.tag-blue{cursor:pointer;color:#6DA0E3;}.tag-blue:hover{color:#6290cc;}
  html{line-height:1.15;-webkit-text-size-adjust:100%;}
  body{margin:0;}
  main{display:block;}
  h1{font-size:2em;margin:0.67em 0;}
  hr{box-sizing:content-box;height:0;overflow:visible;}
  pre{font-family:monospace,monospace;font-size:1em;}
  a{background-color:transparent; margin-top:5px}
  abbr[title]{border-bottom:none;-webkit-text-decoration:underline;text-decoration:underline;-webkit-text-decoration:underline dotted;text-decoration:underline dotted;}
  b,strong{font-weight:bolder;}
  code,kbd,samp{font-family:monospace,monospace;font-size:1em;}
  small{font-size:80%;}
  sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline;}
  sub{bottom:-0.25em;}
  sup{top:-0.5em;}
  img{border-style:none;}
  button,input,optgroup,select,textarea{font-family:inherit;font-size:100%;line-height:1.15;margin:0;}
  button,input{overflow:visible;}
  button,select{text-transform:none;}
  button,[type='button'],[type='reset'],[type='submit']{-webkit-appearance:button;}
  button::-moz-focus-inner,[type='button']::-moz-focus-inner,[type='reset']::-moz-focus-inner,[type='submit']::-moz-focus-inner{border-style:none;padding:0;}
  button:-moz-focusring,[type='button']:-moz-focusring,[type='reset']:-moz-focusring,[type='submit']:-moz-focusring{outline:1px dotted ButtonText;}
  fieldset{padding:0.35em 0.75em 0.625em;}
  legend{box-sizing:border-box;color:inherit;display:table;max-width:100%;padding:0;white-space:normal;}
  progress{vertical-align:baseline;}
  textarea{overflow:auto;}[type='checkbox'],[type='radio']{box-sizing:border-box;padding:0;}[type='number']::-webkit-inner-spin-button,[type='number']::-webkit-outer-spin-button{height:auto;}[type='search']{-webkit-appearance:textfield;outline-offset:-2px;}[type='search']::-webkit-search-decoration{-webkit-appearance:none;}::-webkit-file-upload-button{-webkit-appearance:button;font:inherit;}details{display:block;}
  summary{display:list-item;}
  template{display:none;}[hidden]{display:none;}
  body{font-family:-apple-system,BlinkMacSystemFont,"PingFang SC",Helvetica,Tahoma,Arial,"Microsoft YaHei",微软雅黑,黑体,Heiti,sans-serif,SimSun,宋体,serif;background-color:#F7F7F7;}
  *{margin:0;padding:0;outline:none;}
  button,input,optgroup,select,textarea{font-family:inherit;}

  @media screen and (-webkit-min-device-pixel-ratio:2),screen and (min-resolution:2dppx)
  {body{-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale;}}
</style>
  <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
  <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
  <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
  <script type="application/javascript" src="md5.js"></script>
</head><body class=""><div id="root"><div class="StyledBackground-sc-1duRon lgVKQA">
  <div class="transparent LogoWrapper-sc-1duRon-1 dEKxIf"><a class="sc-1I1iYs-1 gXtlRg" href=#>
    <svg width="133" height="26" xmlns="http://www.w3.org/2000/svg">
  <!-- Created with Method Draw - http://github.com/duopixel/Method-Draw/ -->
  <g>>
    <rect fill="#F7F7F7" id="canvas_background" height="28" width="135" y="-1" x="-1"/>
    <g display="none" overflow="visible" y="0" x="0" height="100%" width="100%" id="canvasGrid">
      <rect fill="url(#gridpattern)" stroke-width="0" y="0" x="0" height="100%" width="100%"/>
    </g>
  </g>
  <g>
    <text xml:space="preserve" text-anchor="start" font-family="Helvetica, Arial, sans-serif" font-size="24" id="svg_1" y="21.6" x="0" stroke-width="0" stroke="#000" fill="#426373">Monkey Doc</text>
  </g>
</svg></a></div>
  <div class="top" id="app">
    <div class="secondtop">
      <div class="thirdtop">
        <div class="main">
          <div class="title">登录</div>
          <div class="swith">
            <div class="form-wrapper"><div class="StyledLogin-sc-2oZUsG bZrWhJ">
              <div>
                <div class="upper" type="mobileOrEmail">
                  <label class="label">输入注册手机号或邮箱</label>
                  <div class="input">
                    <input type="text" placeholder="" autofocus="" v-model="userinfo.account" name="mobileOrEmail"></div></div>
                <div class="upper" type="password">
                  <label class="label">输入密码</label>
                  <div class="input">
                    <input type="password" placeholder="" v-model="userinfo.password2" name="password">
                    <span class="tiShi">{{ userinfo.tishi}}</span>
                    <div class="forgetPass tag-blue">忘记密码？</div></div></div>
                <button class="sm-button submit sc-1n784rm-0 lower" type="black" @click="login">立即登录</button></div>
              <div class="bottom">
              </div></div></div></div></div></div></div>
  <div class="StyledSwitch-sc-1duRon-2 dlXXuA">
    <div class="sm-btn-group to-login StyledButtonGroups-sc-1duRon-5 fKoDQx sc-17dnj82-0 dfsiVH">
      <span class="switch-tips">没有帐号？请</span>
      <button class="sm-button  sc-1n784rm-0 sfCUt"  @click="logon" type="default" >注册</button></div></div></div>
  <div id="ToastsContainer_ShimoUI">
    <div class="Toastify"></div></div></div></div>
<div id="feedback-root"></div>
<div class="xl-chrome-ext-bar" id="xl_chrome_ext_{4DB361DE-01F7-4376-B494-639E489D19ED}" style="display: none;">
  <div class="xl-chrome-ext-bar__logo"></div>
</div>
<script>
  function readData (key) {
    if(window.localStorage.getItem(key))
      return JSON.parse(window.localStorage.getItem(key));
    else
      return '';
  }
  function saveData (key,data) {
    window.localStorage.setItem(key, JSON.stringify(data))
  }
  var b=new Vue({
    el:"#app",
    data:{
      userinfo:{
        getURL:'https://bird.ioliu.cn/v1?url=http://monkeydoc.liadrinz.cn/rest/user.json?tel=',
        tel: '',
        email: '',
        password: '',
        password2:'',
        token:'',
        tishi:'',
        account:'',
      }
    },
    mounted: function () {
      console.log(this.userinfo.token)
      if(this.userinfo.token=='')
        return
      else{
        let val={
          token:"this.userinfo.token",
        }
        axios.post("http://localhost:8080/MonkeyDocsStruts_war_exploded/business/userLogin.action",val,{headers:{token:this.userinfo.token}}).then(
                res=>{
                  console.log(res)
                  if(res.headers.responsemsg=="login_succeed")
                  {
                    window.location.href='./DocManager.html';
                  }
                  else
                    return;
                }
        ).catch(
                err=>{
                  console.log(err);
                }
        )
      }
    },
    methods:{
      post:function(){
        if(this.userinfo.account.indexOf("@") == -1)
          this.userinfo.tel=this.userinfo.account;
        else
          this.userinfo.email=this.userinfo.account;
        let val={
          tel:this.userinfo.tel,
          email:this.userinfo.email,
          password:hex_md5(this.userinfo.password2),
        };
        console.log(hex_md5(this.userinfo.password2))
        axios.post("http://localhost:8080/MonkeyDocsStruts_war_exploded/business/userLogin.action",val).then(
                res=>{
                  console.log(res)
          if(res.headers.responsemsg=="User_does_not_exists")
        this.userinfo.tishi='手机号(邮箱）格式有误或尚未注册';
      else if(res.headers.responsemsg=="usr_name_or_psw_wrong")
          this.userinfo.tishi='手机号(邮箱)或密码输入错误';
      else  if(res.headers.responsemsg=="error")
          alert("error");
        else {
          console.log('登陆成功');
          console.log(res.headers.responsemsg);
          saveData("token",res.headers.responsemsg)
            saveData("userid",res.headers.userid)
          //window.location.href='./DocManager.html';
        }
      }
      ).catch(
                err=>{
          console.log(err);
      }
      )
      },
      login(){
        console.log(this.userinfo.token);
        if(this.userinfo.account=='')
        {this.userinfo.tishi='手机号(邮箱)不能为空';return;}
        if(this.userinfo.password2==''){this.userinfo.tishi='密码不能为空';return;}
        else
          this.post();
      },
      logon(){
        window.location.href='logon.jsp';
      },
    }
  });
</script>
</body>
</html>