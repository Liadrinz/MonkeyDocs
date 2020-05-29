<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
    <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
</head>
<body>
<div class="top" id="app">
    <button class="sm-button submit sc-1n784rm-0 lower" type="black" @click="post">立即登录</button></div>
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
                id: '',
                tel: '13120171889',
                email: '435438602@qq.com',
                userName: '',
                password: '',
                password2:'',
                token:'',
                tishi:'',
            }
        },
        methods:{
            post:function(){
                let val={
                    tel:"13120171889",
                    password:"78c8a34620020484ebcefa98c926c8ba1ed7b2621eee7c4f56e528b1c26fe30a",
                };
                axios.post("http://localhost:8080/MonkeyDocsStruts_war_exploded/business/userLogin.action",val,{headers:{token:this.userinfo.token}}).then(
                    res=>{
                        console.log(res)
                        if(res.headers.responsemsg=="User_does_not_exists")
                            this.userinfo.tishi='手机号格式有误或尚未注册';
                        else if(res.headers.responsemsg=="usr_name_or_psw_wrong")
                            this.userinfo.tishi='用户名或密码输入错误';
                        else {
                            console.log('登陆成功');
                            console.log(res.headers.responsemsg);
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
                if(this.userinfo.tel=='')
                {this.userinfo.tishi='手机号不能为空';return;}
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