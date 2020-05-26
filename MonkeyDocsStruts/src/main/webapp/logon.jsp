<%--
  Created by IntelliJ IDEA.
  User: tyf
  Date: 2020/4/22
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <style type="text/css">
        *{margin: 0px;padding: 0px;}
        a{color: black;text-decoration: none;}
        body{font-family:-apple-system,BlinkMacSystemFont,"PingFang SC",Helvetica,Tahoma,Arial,"Microsoft YaHei",微软雅黑,黑体,Heiti,sans-serif,SimSun,宋体,serif;background-color:#F7F7F7;}
        .btd_login{height: 38px;width: 50px;border: 1px solid silver;border-radius: 4px;font-size: 16px;cursor: pointer;margin-left: 10px;}
        #app1{width: 360px;margin:100px auto;}
        label{display: inline-block;width:70px;height: 38px;text-align: center;}
        .tiShi{font-size: 12px;color:red;margin:0 0 10px 70px;}
        .input{border: none;width:75%;padding: 11px 10px;color: #41464b;font-size: 12px;vertical-align: top;height: 38px;line-height: 38px;position: relative;background: #fff;height: 30px;box-shadow: inset 0 1px 3px 0 #e5e5e5;box-sizing: border-box;border-radius: 2px;border: 1px solid #e5e5e5;}
        textarea{resize: none;border-radius: 4px;}
        .checkbox{font-size: 14px;margin-left: 60px;}
        .now_login{position: fixed;z-index: 2;top: 9px;right: 15px;opacity: 1;}
        .dEKxIf{position:fixed;top:5px;left:30px;width:100%;z-index:1;height:50px;}.dEKxIf a{display:inline-block;}
        .sm-button{left:9px;font-size:14px;color:#666;font-weight:400;min-width:52px;width:52px;padding:0;box-sizing: border-box;display: inline-block;border-radius: 2px;border-style: solid;background-color: #fff;text-align: center;text-decoration: none;cursor: pointer;transition: opacity 0.3s,border-color 0.3s;  border-width: 0;background-image: linear-gradient(#fdfdfd,#f8f8f8);box-shadow: 0 1px 1px 0 rgba(0,0,0,0.11);position: relative;height: 30px;line-height: 28px;}
        .switch-tips {font-weight: 400;margin-right: 10px;margin: 0;padding: 0;outline: none;font-size: 14px;color: #494949;text-align: center;cursor: pointer;}
        #btd{color: #fff;border-color: #41464b;background-image: linear-gradient( #41464b,#2c3033 );width: 100%;height: 40px;font-weight: 500;font-size: 14px;margin-top: 10px;}
        #successful{width: 300px;height: 200px;border: 1px solid silver;position: fixed;left: 50%;bottom: 50%;background:#D8D8D8;display: none;}
        #btn{width: 50px;height: 30px;display: inline-block;float: right;margin-right: 15px;}
    </style>
    <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
    <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
</head>
<body>
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
<div id="app">
    <div id="successful">
        <h3 style="text-align: center;margin-top: 80px;margin-bottom: 40px;">注册成功，快去登录吧!</h3>
        <button id="btn" @click="close" >取消</button>
        <button @click="login" id="btn">登录</button>
    </div>
    <div class ="now_login"><span class="switch-tips">已有账号？请</span>
        <button class="sm-button  sc-1n784rm-0 sfCUt" @click="login" type="default">登录</button></div>
    <div id="app1">
        <label>用户名</label><input type="text" placeholder="请设置用户名"  v-model="userInfo.username" class="input" @blur="a"><br>
        <span class="tiShi">{{ tiShi.tishi1 }}</span><br>
        <label>设置密码</label><input type="password" placeholder="请设置登录密码" v-model="userInfo.password1"class="input" @blur="b"><br>
        <span class="tiShi">{{ tiShi.tishi2 }}</span><br>
        <label>确认密码</label><input type="password" v-model="userInfo.password2"class="input" @blur="c"><br>
        <span class="tiShi">{{ tiShi.tishi3}}</span><br>
        <label>真实姓名</label><input type="text" v-model="userInfo.name"class="input" @blur="d"><br>
        <span class="tiShi">{{ tiShi.tishi4}}</span><br>
        <label>身份证号码</label><input type="text" v-model="userInfo.idcard"class="input" @blur="h"><br>
        <span class="tiShi">{{ tiShi.tishi8}}</span><br>
        <label>电子邮箱</label><input type="text" v-model="userInfo.email"class="input" @blur="g"><br>
        <span class="tiShi">{{ tiShi.tishi7}}</span><br>
        <label style=" position: relative;left:15px">性别</label>
        <input type="radio" id="male" value="male" v-model="userInfo.sex" style="margin-left:0px">
        <label for="male" style=" position: relative;right:20px">男</label>
        <input type="radio" id="female" value="female" v-model="userInfo.sex">
        <label for="female"style=" position: relative;right:20px">女</label><br><br>
        <label>手机号</label><input type="text" placeholder="请输入手机号码"  v-model="userInfo.phonenum"class="input" @blur="e"><br>
        <span class="tiShi">{{ tiShi.tishi5}}</span><br>
        <label>公司地址</label><textarea rows="5" cols="26"placeholder="请输入您的公司地址" v-model="userInfo.address" @blur="f"></textarea><br>
        <span class="tiShi">{{ tiShi.tishi6}}</span><br>
        <div class="checkbox"><input type="checkbox" style="margin-right: 10px;" v-model="userInfo.checkbox">阅读并接受<a href="" >《xxx用户协议》</a></div>
        <button id="btd" @click="flag" :disabled="btn">立即注册</button>
    </div>
</div>
</body>
<script>
    var vm=new Vue({
        el:'#app',
        data:{
            isReturn: false,
            btn:false,
            tiShi:{
                tiShi1:'',
                tishi2:'',
                tishi3:'',
                tishi4:'',
                tishi5:'',
                tishi6:'',
                tishi7:'',
                tishi8:'',
            },
            userInfo:{
                username:'lxqzxf',
                password1:'751603s',
                password2:'751603s',
                phonenum:'18810018732',
                name:'田怡凡',
                address:'sadsadasdasd',
                sex: '',
                email:'648897367@qq.com',
                idcard:'640381199901093617',
                checkbox:true,
                notcheck:true,
                logon_res:false,
            },
            userArr:[]
        },
        methods:{
            getAuthCode:function () {
                const verification =this.ReginForm.tel;
                const url = " "
                console.log("url",url);
                this.$http.get(url).then(function (response) {
                    console.log("请求成功",response)
                }, function (error) {
                    console.log("请求失败",error);
                })
                this.sendAuthCode = false;
                //设置倒计时秒
                this.auth_time = 10;
                var auth_timetimer = setInterval(()=>{
                    this.auth_time--;
                if(this.auth_time<=0){
                    this.sendAuthCode = true;
                    clearInterval(auth_timetimer);
                }
            }, 1000);
            },
            // 封装注册发送请求方法
            thisAjax(){
                const passwordData=this.userInfo.password1;
                const phoneData =this.userInfo.phonenum;
                const usernameData=this.userInfo.username;
                const idData=this.userInfo.idcard;
                const emailData=this.userInfo.email;
                axios.post("business/userLogon.action",{userName:usernameData,password:passwordData,tel:phoneData,email:emailData}).then(
                    res=>
                {
                    console.log(res);
                    if(res.headers.responsemsg=="logon_succeed")
                        this.userInfo.logon_res=true;
                    else if(res.headers.responsemsg=="user_has_been_logon")
                        this.userInfo.logon_res=false;
                    if(this.userInfo.logon_res) {
                        var box = document.getElementById("successful");
                        box.style.display = 'block';
                        // window.location.href='../登录/登录页面.html';
                    }
                    else{
                        this.tiShi.tishi5='手机号已注册';
                        return;
                    }
                }).catch(
                    err=>{
                    }
                )
            },
            reset () {
                this.$refs.ReginForm.resetFields()
            },
            tologin () {
                //已经注册过跳转到登入界面
                this.$router.push('/phoneLogin')
            },
            //用户名
            a(){
                this.tiShi.tishi1 = '';
                this.isReturn = true;
                var a=/^(\w)+$/;//小写字母组成
                if (!this.userInfo.username) this.tiShi.tishi1='用户名不能为空';
                else if(!a.test(this.userInfo.user)){
                    this.isReturn = true;
                    this.tiShi.tishi1="由小写字母组成"
                }
            },
            //密码
            b (){
                this.tiShi.tishi2 = '';
                this.isReturn = true;
                var p=/(\w)*(\d)+[a-zA-Z]+/;
                if (!this.userInfo.password1) {
                    this.isReturn = true;
                    this.tiShi.tishi2='密码不能为空';
                }
                else if(!p.test(this.userInfo.password1)||this.userInfo.password1.length<5){
                    this.isReturn = true;
                    this.tiShi.tishi2="密码应至少包含1位数字,1位字母,且长度不小于5"
                }
            },
            //第二遍密码
            c(){
                this.tiShi.tishi3 = '';
                this.isReturn = true;
                if (!this.userInfo.password2) {
                    this.isReturn = true;
                    this.tiShi.tishi3='确认密码不能为空';
                }
                else if(this.userInfo.password1 != this.userInfo.password2){
                    this.isReturn = true;
                    this.tiShi.tishi3='两次密码不一致';
                }
            },
            //真实姓名
            d(){
                this.tiShi.tishi4 = '';
                this.isReturn = true;
                var r=/[^\x00-\xff]+/
                if (!this.userInfo.name){this.tiShi.tishi4='姓名不能为空';this.isReturn = true;}
                else if(!r.test(this.userInfo.name)){this.tiShi.tishi4='姓名格式不正确,请使用中国汉字';this.isReturn=true;}
            },
            //手机号
            e(){
                this.tiShi.tishi5 = '';
                this.isReturn = true;
                var n=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
                if (!this.userInfo.phonenum)
                {
                    this.tiShi.tishi5='手机号不能为空';
                    this.isReturn = true;
                }
                else if (!n.test(this.userInfo.phonenum)) {this.tiShi.tishi5='手机号码格式不正确';this.isReturn = true;}
            },
            f(){
                this.tiShi.tishi6 = '';
                this.isReturn = true;
                if (!this.userInfo.address) {this.tiShi.tishi6='请输入您的公司或学校地址';this.isReturn = true;}
            },
            g(){
                this.tiShi.tishi7 = '';
                this.isReturn = true;
                var r = /[\w]+@(\w)+\.[a-zA-Z]+/
                if (!this.userInfo.email) {this.tiShi.tishi7='电子邮箱不能为空';this.isReturn = true;}
                else if(!r.test(this.userInfo.email)){this.tiShi.tishi7='电子邮箱格式不正确';this.isReturn = true;}
            },
            h(){
                this.tiShi.tishi8='';
                this.isReturn=true;
                var r=/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
                if(!this.userInfo.idcard){this.tiShi.tishi8='身份证号码不能为空';this.isReturn=true;}
                else if(!r.test(this.userInfo.idcard)){this.tiShi.tishi8='身份证号码格式不正确';this.isReturn=true;}
            },
            flag (){
                //alert(1)
                this.tiShi.tishi1 = '';
                this.tiShi.tishi2 = '';
                this.tiShi.tishi3 = '';
                this.tiShi.tishi4 = '';
                this.tiShi.tishi5 = '';
                this.tiShi.tishi6 = '';
                this.tiShi.tishi7='';
                this.tiShi.tishi8='';
                this.isReturn = false;
                //用户名
                var a=/^(\w)+$/;//小写字母组成
                if (!this.userInfo.username)
                {
                    this.tiShi.tishi1='用户名不能为空';
                    this.isReturn = true;
                }
                else if(!a.test(this.userInfo.username)){
                    this.isReturn = true;
                    this.tiShi.tishi1="由小写字母组成"
                }
                var p=/(\w)*(\d)+[a-zA-Z]+/;
                if (!this.userInfo.password1) {
                    this.isReturn = true;
                    this.tiShi.tishi2='密码不能为空';
                }
                else if(!p.test(this.userInfo.password1)){
                    this.isReturn = true;
                    this.tiShi.tishi2="密码应至少包含1位数字,1位字母,且长度不小于5"
                }
                //确认密码
                if (!this.userInfo.password2) {
                    this.isReturn = true;
                    this.tiShi.tishi3='确认密码不能为空';
                }
                else if(this.userInfo.password1 != this.userInfo.password2){
                    this.isReturn = true;
                    this.tiShi.tishi3='两次密码不一致';
                }
                //姓名
                var e=/[^\x00-\xff]+/
                if (!this.userInfo.name){this.tiShi.tishi4='姓名不能为空';this.isReturn = true;}
                else if(!e.test(this.userInfo.name)){this.tiShi.tishi4='姓名格式不正确,请使用中国汉字';this.isReturn=true;}
                //手机号
                var n=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
                if (!this.userInfo.phonenum)
                {
                    this.tiShi.tishi5='手机号不能为空';
                    this.isReturn = true;
                }
                else if (!n.test(this.userInfo.phonenum)) {this.tiShi.tishi5='手机号码格式不正确';this.isReturn = true;}
                //家庭住址
                if (!this.userInfo.address) {this.tiShi.tishi6='请输入您的公司地址';this.isReturn = true;}
                if(!this.userInfo.idcard){this.tiShi.tishi8='身份证号码不能为空';this.isReturn = true;}
                var r = /[\w]+@(\w)+\.[a-zA-Z]+/
                //邮箱
                if(!this.userInfo.email){this.tiShi.tishi7='电子邮箱不能为空';this.isReturn = true;}
                else if(!r.test(this.userInfo.email)){this.tiShi.tishi7='电子邮箱格式不正确';this.isReturn = true;}
                //同意协议
                if (this.userInfo.checkbox!=true) {this.btn=false;alert('是否同意该协议');this.isReturn = true;return}
                //如果有这些提示就return
                if (this.isReturn) return;
                this.thisAjax();
            },
            login(){
                window.location.href='./login.jsp';
            },
            close(){
                var box=document.getElementById("successful");
                box.style.display="none";
            }

        }
    })
</script>
</html>